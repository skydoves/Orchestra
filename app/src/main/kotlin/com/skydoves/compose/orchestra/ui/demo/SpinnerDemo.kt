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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skydoves.compose.orchestra.R
import com.skydoves.compose.orchestra.theme.amber700
import com.skydoves.compose.orchestra.theme.blue200
import com.skydoves.compose.orchestra.theme.white87
import com.skydoves.orchestra.spinner.Spinner

@Composable
fun SpinnerDemo(modifier: Modifier = Modifier) {
  Surface(
    color = MaterialTheme.colors.background,
    modifier = modifier.fillMaxSize()
  ) {
    Column(modifier = modifier.padding(4.dp)) {
      val (selectedItem0, setSelectedItem0) = remember { mutableStateOf("Choose a question") }
      Spinner<String>(
        text = selectedItem0,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
          .background(blue200)
          .align(Alignment.CenterHorizontally),
        itemListRes = R.array.list_spinner,
        color = Color.White,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center,
        itemHeight = 46.dp,
        showDivider = true,
        dividerColor = white87,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        spinnerPadding = 16.dp,
        spinnerBackgroundColor = MaterialTheme.colors.onBackground,
        onSpinnerItemSelected = { index, item ->
          setSelectedItem0(item)
        }
      )

      val coffeeList = remember { listOf("Americano", "Cold Brew", "Espresso", "Latte") }
      val (selectedItem1, setSelectedItem1) = remember { mutableStateOf("Choose your coffee") }
      Spinner(
        text = selectedItem1,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
          .background(amber700)
          .align(Alignment.CenterHorizontally),
        itemList = coffeeList,
        color = Color.White,
        itemHeight = 46.dp,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center,
        dividerColor = white87,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        spinnerPadding = 16.dp,
        spinnerBackgroundColor = MaterialTheme.colors.onBackground,
        onSpinnerItemSelected = { index, item ->
          setSelectedItem1(item)
        }
      )
    }
  }
}
