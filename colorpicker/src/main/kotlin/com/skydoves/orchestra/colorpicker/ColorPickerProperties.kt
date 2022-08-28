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

package com.skydoves.orchestra.colorpicker

import android.graphics.Point
import android.graphics.drawable.Drawable
import androidx.annotation.FloatRange
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skydoves.colorpickerview.ActionMode

/**
 * Represents properties to apply [ColorPicker] composable.
 *
 * @property palette The palette drawable to configure color picker.
 * @property selector The selector, which is draggable by user to select desired colors.
 * @property actionMode The way of trigger color listener.
 * @property debounceDuration The duration of debounce color listener.
 * @property selectorSize The width and height size of the selector.
 * @property selectorPoint The initial x, y point of the selector.
 * @property selectorAlpha The alpha of the selector.
 */
public data class ColorPickerProperties(
  val palette: Drawable? = null,
  val selector: Drawable? = null,
  val actionMode: ActionMode = ActionMode.ALWAYS,
  val debounceDuration: Int = 0,
  val selectorSize: Dp = 12.dp,
  val selectorPoint: Point? = null,
  @FloatRange(from = 0.0, to = 1.0) val selectorAlpha: Float = 1.0f
)
