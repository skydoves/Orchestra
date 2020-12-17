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

package com.skydoves.compose.orchestra.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skydoves.compose.orchestra.theme.purple200
import com.skydoves.compose.orchestra.ui.navigation.Actions

@Composable
fun Home(actions: Actions, modifier: Modifier = Modifier) {
  val items = remember { listOf("Balloon", "ColorPicker", "Spinner") }
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colors.background)
  ) {
    LazyColumn(
      contentPadding = PaddingValues(4.dp)
    ) {
      items(
        items = items,
        itemContent = { item ->
          val selectItem: () -> Unit = when (item) {
            "Balloon" -> {
              actions.selectOnBalloon
            }
            "ColorPicker" -> {
              actions.selectOnColorPicker
            }
            "Spinner" -> {
              actions.selectOnSpinner
            }
            else -> {
              { }
            }
          }
          Item(
            modifier = modifier,
            title = item,
            selectItem = selectItem
          )
        }
      )
    }
  }
}

@Composable
fun Item(
  modifier: Modifier,
  title: String,
  selectItem: () -> Unit
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .padding(6.dp)
      .height(86.dp),
    shape = RoundedCornerShape(8.dp)
  ) {
    ConstraintLayout(
      modifier = Modifier
        .background(purple200)
        .clickable(onClick = { selectItem() })
    ) {
      val titleRef = createRef()
      Text(
        text = title,
        modifier = Modifier.constrainAs(titleRef) {
          centerVerticallyTo(parent)
          centerHorizontallyTo(parent)
        },
        style = MaterialTheme.typography.h2,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
      )
    }
  }
}
