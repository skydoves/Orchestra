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

package com.skydoves.compose.orchestra.ui.main

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import com.skydoves.compose.orchestra.model.MockUtil
import com.skydoves.compose.orchestra.ui.Home
import com.skydoves.compose.orchestra.ui.demo.BalloonDemo
import com.skydoves.compose.orchestra.ui.demo.ColorPickerDemo
import com.skydoves.compose.orchestra.ui.demo.SpinnerDemo
import com.skydoves.compose.orchestra.ui.navigation.Actions
import com.skydoves.compose.orchestra.ui.navigation.AmbientBackDispatcher
import com.skydoves.compose.orchestra.ui.navigation.Destination
import com.skydoves.compose.orchestra.ui.navigation.Navigator

@Composable
fun Main(backDispatcher: OnBackPressedDispatcher) {
  val navigator: Navigator<Destination> = rememberSavedInstanceState(
    saver = Navigator.saver(backDispatcher)
  ) {
    Navigator(Destination.Home, backDispatcher)
  }
  val actions = remember(navigator) { Actions(navigator) }
  Providers(AmbientBackDispatcher provides backDispatcher) {
    Crossfade(navigator.current) { destination ->
      when (destination) {
        Destination.Home -> Home(actions)
        is Destination.BalloonDemo -> {
          BalloonDemo(posters = MockUtil.getMockPosters())
        }
        is Destination.ColorPickerDemo -> ColorPickerDemo()
        is Destination.SpinnerDemo -> SpinnerDemo()
      }
    }
  }
}
