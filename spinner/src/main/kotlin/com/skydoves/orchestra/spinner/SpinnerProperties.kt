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

package com.skydoves.orchestra.spinner

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

/**
 * Represents properties to apply [Spinner] composable.
 *
 * @property color The color of the spinner items and the text.
 * @property fontSize The font size of the spinner.
 * @property fontStyle The font style of the spinner.
 * @property fontWeight The font weight of the spinner.
 * @property fontFamily the font family of the spinner.
 * @property letterSpacing The letter spacing of the spinner text.
 * @property textDecoration The text decoration of the spinner text.
 * @property textAlign The text align of the spinner.
 * @property popupWidth The width size of the popup.
 * @property popupHeight The height size of the popup.
 * @property itemHeight The item height size of the spinner.
 * @property lineHeight The line height size of the spinner.
 * @property overflow Decides overflow of the spinner.
 * @property maxLines The max lines of the spinner.
 * @property showDivider Weather divider or not.
 * @property dividerSize The size of the divider.
 * @property dividerColor The color of the divider.
 * @property spinnerPadding The padding of the spinner items.
 * @property spinnerBackgroundColor The background color of the spinner popup.
 */
public data class SpinnerProperties(
  val color: Color = Color.Unspecified,
  val fontSize: TextUnit = TextUnit.Unspecified,
  val fontStyle: FontStyle? = null,
  val fontWeight: FontWeight? = null,
  val fontFamily: FontFamily? = null,
  val letterSpacing: TextUnit = TextUnit.Unspecified,
  val textDecoration: TextDecoration? = null,
  val textAlign: TextAlign? = null,
  val popupWidth: Dp? = null,
  val popupHeight: Dp? = null,
  val itemHeight: Dp? = null,
  val lineHeight: TextUnit = TextUnit.Unspecified,
  val overflow: TextOverflow = TextOverflow.Clip,
  val softWrap: Boolean = true,
  val maxLines: Int = Int.MAX_VALUE,
  val showDivider: Boolean = false,
  val dividerSize: Dp = 0.5.dp,
  val dividerColor: Color = Color.Unspecified,
  val spinnerPadding: Dp = 0.dp,
  val spinnerBackgroundColor: Color = Color.Unspecified
)
