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

package com.skydoves.compose.orchestra.ui.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.compose.orchestra.theme.purple500
import com.skydoves.orchestra.colorpicker.AlphaSlideBar
import com.skydoves.orchestra.colorpicker.AlphaTileBox
import com.skydoves.orchestra.colorpicker.BrightnessSlideBar
import com.skydoves.orchestra.colorpicker.ColorPicker

@Composable
fun ColorPickerDemo(modifier: Modifier = Modifier) {
  val (selectedColor, setSelectedColor) = remember { mutableStateOf(ColorEnvelope(0)) }
  Surface(
    color = MaterialTheme.colors.onBackground,
    modifier = modifier.fillMaxSize()
  ) {
    ConstraintLayout(
      modifier = Modifier.padding(horizontal = 10.dp, vertical = 40.dp)
    ) {
      val (colorPicker, square, colorCode) = createRefs()
      ColorPicker(
        modifier = modifier.constrainAs(colorPicker) {
          centerHorizontallyTo(parent)
        }.fillMaxWidth().height(400.dp),
        onColorListener = { envelope, _ ->
          setSelectedColor(envelope)
        },
        initialColor = purple500,
        children = { colorPickerView ->
          Column(modifier = Modifier.padding(top = 32.dp)) {
            Box(modifier = Modifier.padding(vertical = 6.dp)) {
              AlphaSlideBar(
                modifier = Modifier.fillMaxWidth().height(30.dp)
                  .clip(RoundedCornerShape(4.dp)),
                colorPickerView = colorPickerView
              )
            }
            Box(modifier = Modifier.padding(vertical = 6.dp)) {
              BrightnessSlideBar(
                modifier = Modifier.fillMaxWidth().height(30.dp)
                  .clip(RoundedCornerShape(4.dp)),
                colorPickerView = colorPickerView
              )
            }
          }
        }
      )
      AlphaTileBox(
        modifier = modifier.constrainAs(square) {
          bottom.linkTo(parent.bottom)
          centerHorizontallyTo(parent)
        }.size(64.dp).clip(RoundedCornerShape(4.dp))
      ) {
        it.setBackgroundColor(selectedColor.color)
      }
      Text(
        text = "#${selectedColor.hexCode}",
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Center,
        modifier = Modifier.constrainAs(colorCode) {
          centerHorizontallyTo(parent)
          bottom.linkTo(square.top)
        }.padding(8.dp)
      )
    }
  }
}
