/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("ColorPicker")
@file:JvmMultifileClass

package com.skydoves.orchestra.colorpicker

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.skydoves.colorpickerview.ActionMode
import com.skydoves.colorpickerview.AlphaTileView
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.flag.BubbleFlag
import com.skydoves.colorpickerview.flag.FlagView
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import com.skydoves.colorpickerview.sliders.AlphaSlideBar
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar

/*
 * Create a ColorPicker composable for getting colors from any images by tapping on the desired color.
 * https://github.com/skydoves/colorpickerview
 *
 * ```
 * ColorPicker(
 *      modifier = modifier.constrainAs(colorPicker) {
 *        centerHorizontallyTo(parent)
 *      }.fillMaxWidth().height(400.dp),
 *      onColorListener = { envelope, _ ->
 *        setSelectedColor(envelope)
 *      },
 *      children = { colorPickerView ->
 *        Column(modifier = Modifier.padding(top = 32.dp)) {
 *          Box(modifier = Modifier.padding(vertical = 6.dp)) {
 *            AlphaSlideBar(
 *              modifier = Modifier.fillMaxWidth().height(30.dp)
 *                .clip(RoundedCornerShape(4.dp)),
 *              colorPickerView = colorPickerView
 *            )
 *          }
 *          Box(modifier = Modifier.padding(vertical = 6.dp)) {
 *            BrightnessSlideBar(
 *              modifier = Modifier.fillMaxWidth().height(30.dp)
 *                .clip(RoundedCornerShape(4.dp)),
 *              colorPickerView = colorPickerView
 *            )
 *          }
 *        }
 *      }
 *    )
 * ```
 */
@Composable
public fun ColorPicker(
  modifier: Modifier = Modifier,
  context: Context = LocalContext.current,
  onColorListener: (ColorEnvelope, Boolean) -> Unit,
  palette: Drawable? = null,
  selector: Drawable? = null,
  flagView: FlagView? = BubbleFlag(context),
  actionMode: ActionMode = ActionMode.ALWAYS,
  debounceDuration: Int = 0,
  selectorSize: Dp = 12.dp,
  selectorPoint: Point? = null,
  @FloatRange(from = 0.0, to = 1.0) selectorAlpha: Float = 1.0f,
  initialColor: Color? = null,
  children: @Composable (ColorPickerView) -> Unit,
  update: (ColorPickerView) -> Unit = {}
) {
  val colorPickerView = remember {
    ColorPickerView.Builder(context)
      .setActionMode(actionMode)
      .setSelectorAlpha(selectorAlpha)
      .setSelectorSize(context.dp2Px(selectorSize))
      .setDebounceDuration(debounceDuration)
      .setColorListener(
        ColorEnvelopeListener { colorEnvelope, fromUser ->
          onColorListener(colorEnvelope, fromUser)
        }
      ).build().apply {
        post {
          selector?.let { setSelectorDrawable(it) }
          flagView?.let { setFlagView(it) }
          initialColor?.let { setInitialColor(initialColor.toArgb()) }
          palette?.let { setPaletteDrawable(it) } ?: setHsvPaletteDrawable()
          selectorPoint?.let { setSelectorPoint(it.x, it.y) }
            ?: selectCenter()
        }
      }
  }
  Column {
    AndroidView(
      factory = { colorPickerView },
      modifier = modifier
    ) {
      it.post {
        update(it)
      }
    }
    children(colorPickerView)
  }
}

/**
 * Create an AlphaSlideBar composable and attach to a [ColorPickerView].
 * AlphaSlideBar composable can be created in the ColorPicker's children composable.
 *
 * ```
 * ColorPicker(
 *      modifier = modifier.constrainAs(colorPicker) {
 *        centerHorizontallyTo(parent)
 *      }.fillMaxWidth().height(400.dp),
 *      onColorListener = { envelope, _ ->
 *        setSelectedColor(envelope)
 *      },
 *      children = { colorPickerView ->
 *        AlphaSlideBar(
 *          modifier = Modifier.fillMaxWidth().height(30.dp)
 *            .clip(RoundedCornerShape(4.dp)),
 *          selector = R.drawable.wheel,
 *          borderColor = borderColor,
 *          borderSize = 2.dp,
 *          colorPickerView = colorPickerView
 *        )
 *      }
 *    )
 * ```
 */
@Composable
public fun AlphaSlideBar(
  modifier: Modifier = Modifier,
  context: Context = LocalContext.current,
  colorPickerView: ColorPickerView,
  @FloatRange(from = 0.0, to = 1.0) selectedPosition: Float = 1.0f,
  @DrawableRes selector: Int = R.drawable.wheel,
  borderColor: Color = Color.Unspecified,
  borderSize: Dp = 0.dp,
  update: (AlphaSlideBar) -> Unit = {}
) {
  val alphaSlideBar = remember {
    AlphaSlideBar(context).apply {
      post { setSelectorPosition(selectedPosition) }
    }
  }
  AndroidView(
    factory = { alphaSlideBar },
    modifier = modifier
  ) {
    it.setSelectorDrawableRes(selector)
    it.setBorderColor(borderColor.toArgb())
    it.setBorderSize(context.dp2Px(borderSize))
    colorPickerView.attachAlphaSlider(it)
    update(it)
  }
}

/**
 * Create an BrightnessSlideBar composable and attach to a [ColorPickerView].
 * BrightnessSlideBar composable can be created in the ColorPicker's children composable.
 *
 * ```
 * ColorPicker(
 *      modifier = modifier.constrainAs(colorPicker) {
 *        centerHorizontallyTo(parent)
 *      }.fillMaxWidth().height(400.dp),
 *      onColorListener = { envelope, _ ->
 *        setSelectedColor(envelope)
 *      },
 *      children = { colorPickerView ->
 *        BrightnessSlideBar(
 *          modifier = Modifier.fillMaxWidth().height(30.dp)
 *            .clip(RoundedCornerShape(4.dp)),
 *          selector = R.drawable.wheel,
 *          borderColor = borderColor,
 *          borderSize = 2.dp,
 *          colorPickerView = colorPickerView
 *        )
 *      }
 *    )
 * ```
 */
@Composable
public fun BrightnessSlideBar(
  modifier: Modifier = Modifier,
  context: Context = LocalContext.current,
  colorPickerView: ColorPickerView,
  @FloatRange(from = 0.0, to = 1.0) selectedPosition: Float = 1.0f,
  @DrawableRes selector: Int = R.drawable.wheel,
  borderColor: Color = Color.Unspecified,
  borderSize: Dp = 0.dp,
  update: (BrightnessSlideBar) -> Unit = {}
) {
  val brightnessSlideBar = remember {
    BrightnessSlideBar(context).apply {
      post { setSelectorPosition(selectedPosition) }
    }
  }
  AndroidView(
    factory = { brightnessSlideBar },
    modifier = modifier
  ) {
    it.setSelectorDrawableRes(selector)
    it.setBorderColor(borderColor.toArgb())
    it.setBorderSize(context.dp2Px(borderSize))
    colorPickerView.attachBrightnessSlider(it)
    update(it)
  }
}

/*
 * Create an AlphaTileBox for reflecting ARGB colors through a Box.
 *
 * ```
 * AlphaTileBox(
 *      modifier = modifier.constrainAs(square) {
 *        bottom.linkTo(parent.bottom)
 *        centerHorizontallyTo(parent)
 *      }.size(64.dp).clip(RoundedCornerShape(4.dp))
 *    ) {
 *      it.setBackgroundColor(selectedColor.color)
 *    }
 * ```
 */
@Composable
public fun AlphaTileBox(
  modifier: Modifier = Modifier,
  context: Context = LocalContext.current,
  update: (AlphaTileView) -> Unit = {}
) {
  val alphaTileView = remember { AlphaTileView(context) }
  AndroidView(
    factory = { alphaTileView },
    modifier = modifier
  ) {
    update(it)
  }
}

private fun Context.dp2Px(dp: Dp): Int {
  val scale = resources.displayMetrics.density
  return (dp.value * scale).toInt()
}
