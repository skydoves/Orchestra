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

@file:JvmName("Spinner")
@file:JvmMultifileClass

package com.skydoves.orchestra.spinner

import android.content.Context
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ArrayRes
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.material.AmbientTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.LifecycleOwnerAmbient
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.skydoves.powerspinner.PowerSpinnerView
import com.skydoves.powerspinner.SpinnerAnimation
import com.skydoves.powerspinner.createPowerSpinnerView

/*
 * Create a Spinner composable for implementing a lightweight dropdown popup spinner.
 * https://github.com/skydoves/powerspinner
 *
 * Here is an example for creating a spinner using a sting array resource.
 * We should use the String generic type for creating a spinner when we us a string array resource.
 *
 * ```
 * val (selectedItem0, setSelectedItem0) = remember { mutableStateOf("Choose a question") }
 * Spinner<String>(
 *      text = selectedItem0,
 *      modifier = Modifier.fillMaxWidth().padding(16.dp)
 *        .background(blue200)
 *        .align(Alignment.CenterHorizontally),
 *      itemListRes = R.array.list_spinner,
 *      color = Color.White,
 *      style = MaterialTheme.typography.body2,
 *      textAlign = TextAlign.Center,
 *      showDivider = true,
 *      dividerColor = white87,
 *      overflow = TextOverflow.Ellipsis,
 *      maxLines = 1,
 *      spinnerPadding = 16.dp,
 *      spinnerBackgroundColor = MaterialTheme.colors.onBackground,
 *      onSpinnerItemSelected = { index, item ->
 *        setSelectedItem0(item)
 *      }
 *    )
 * ```
 *
 * Here is an another example using a List for creating a spinner.
 * In this case, we don't need to decide a generic type of Spinner.
 *
 * ```
 * val coffeeList = remember { listOf("Americano", "Cold Brew", "Espresso", "Latte") }
 * val (selectedItem1, setSelectedItem1) = remember { mutableStateOf("Choose your coffee") }
 * Spinner(
 *      text = selectedItem1,
 *      modifier = Modifier.fillMaxWidth().padding(16.dp)
 *        .background(amber700)
 *        .align(Alignment.CenterHorizontally),
 *      itemList = coffeeList,
 *      color = Color.White,
 *      style = MaterialTheme.typography.body2,
 *      textAlign = TextAlign.Center,
 *      dividerColor = white87,
 *      overflow = TextOverflow.Ellipsis,
 *      maxLines = 1,
 *      spinnerPadding = 16.dp,
 *      spinnerBackgroundColor = MaterialTheme.colors.onBackground,
 *      onSpinnerItemSelected = { index, item ->
 *        setSelectedItem1(item)
 *      }
 *    )
 * ```
 */
@Composable
fun <T> Spinner(
  text: String = "",
  modifier: Modifier = Modifier,
  color: Color = Color.Unspecified,
  fontSize: TextUnit = TextUnit.Inherit,
  fontStyle: FontStyle? = null,
  fontWeight: FontWeight? = null,
  fontFamily: FontFamily? = null,
  letterSpacing: TextUnit = TextUnit.Inherit,
  textDecoration: TextDecoration? = null,
  textAlign: TextAlign? = null,
  lineHeight: TextUnit = TextUnit.Inherit,
  overflow: TextOverflow = TextOverflow.Clip,
  softWrap: Boolean = true,
  maxLines: Int = Int.MAX_VALUE,
  onTextLayout: (TextLayoutResult) -> Unit = {},
  style: TextStyle = AmbientTextStyle.current,
  context: Context = ContextAmbient.current,
  lifecycleOwner: LifecycleOwner = LifecycleOwnerAmbient.current,
  showDivider: Boolean = false,
  dividerSize: Dp = 0.5.dp,
  dividerColor: Color = Color.Unspecified,
  spinnerPadding: Dp = 0.dp,
  spinnerBackgroundColor: Color = Color.Unspecified,
  onSpinnerItemSelected: (Int, T) -> Unit = { _, _ -> },
  onSpinnerOutsideTouched: (View, MotionEvent) -> Unit = { _, _ -> },
  dismissWhenNotifiedItemSelected: Boolean = true,
  spinnerAnimation: SpinnerAnimation = SpinnerAnimation.NORMAL,
  @ArrayRes itemListRes: Int? = null,
  itemList: List<T> = listOf(),
  update: (PowerSpinnerView) -> Unit = {}
) {
  val spinnerView = remember {
    createPowerSpinnerView(context) {
      setShowArrow(false)
      setShowDivider(showDivider)
      setDividerColor(dividerColor.toArgb())
      setDividerSize(context.dp2Px(dividerSize))
      setLifecycleOwner(lifecycleOwner)
      setSpinnerPopupAnimation(spinnerAnimation)
      setSpinnerPopupBackgroundColor(spinnerBackgroundColor.toArgb())
      setDismissWhenNotifiedItemSelected(dismissWhenNotifiedItemSelected)
      setOnSpinnerOutsideTouchListener { view, motionEvent ->
        onSpinnerOutsideTouched(view, motionEvent)
      }
    }
  }
  with(spinnerView) {
    itemListRes?.let { setItems(it) } ?: setItems(itemList)
    setIsFocusable(true)
    setTextColor(
      if (color != Color.Unspecified) {
        color.toArgb()
      } else {
        style.color.toArgb()
      }
    )
    textSize = if (fontSize != TextUnit.Inherit) {
      fontSize.value
    } else {
      style.fontSize.value
    }
    textAlign?.let { gravity = it.toGravity() }
    val padding = spinnerPadding.value.toInt()
    setPadding(padding, padding, padding, padding)
    spinnerView.setDisableChangeTextWhenNotified(true)
    spinnerView.setOnSpinnerItemSelectedListener<T> { position, item ->
      onSpinnerItemSelected(position, item)
    }
  }
  ConstraintLayout {
    val (spinner, spinnerText) = createRefs()
    AndroidView(
      viewBlock = { spinnerView },
      modifier = modifier.constrainAs(spinner) {
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
      }
    ) {
      update(it)
    }
    Text(
      AnnotatedString(text),
      Modifier.constrainAs(spinnerText) {
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
      },
      color,
      fontSize,
      fontStyle,
      fontWeight,
      fontFamily,
      letterSpacing,
      textDecoration,
      textAlign,
      lineHeight,
      overflow,
      softWrap,
      maxLines,
      emptyMap(),
      onTextLayout,
      style
    )
  }
}

internal fun Context.dp2Px(dp: Dp): Int {
  val scale = resources.displayMetrics.density
  return (dp.value * scale).toInt()
}

internal fun TextAlign.toGravity(): Int {
  return when (this) {
    TextAlign.Center -> Gravity.CENTER
    TextAlign.Start, TextAlign.Left -> Gravity.START
    TextAlign.End, TextAlign.Right -> Gravity.END
    TextAlign.Justify -> Gravity.NO_GRAVITY
  }
}
