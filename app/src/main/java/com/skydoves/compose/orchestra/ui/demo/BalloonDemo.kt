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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.skydoves.compose.orchestra.model.MockUtil
import com.skydoves.compose.orchestra.model.Poster
import com.skydoves.compose.orchestra.theme.DisneyComposeTheme
import com.skydoves.compose.orchestra.ui.StaggeredVerticalGrid
import com.skydoves.compose.orchestra.utils.BalloonUtils
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.orchestra.balloon.BalloonAnchor

@Composable
fun BalloonDemo(
  posters: List<Poster>,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .verticalScroll(rememberScrollState())
      .background(MaterialTheme.colors.background)
  ) {
    StaggeredVerticalGrid(
      maxColumnWidth = 220.dp,
      modifier = Modifier.padding(4.dp)
    ) {
      posters.forEach { poster ->
        Poster(poster = poster)
      }
    }
  }
}

@Composable
fun Poster(
  poster: Poster,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier.padding(4.dp),
    color = MaterialTheme.colors.onBackground,
    elevation = 8.dp,
    shape = RoundedCornerShape(8.dp)
  ) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    ConstraintLayout {
      val (image, title, content, message) = createRefs()
      GlideImage(
        imageModel = poster.poster,
        requestBuilder = Glide.with(LocalContext.current)
          .asBitmap()
          .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
          .transition(BitmapTransitionOptions.withCrossFade()),
        modifier = Modifier.constrainAs(image) {
          centerHorizontallyTo(parent)
          top.linkTo(parent.top)
        }.aspectRatio(0.8f),
        loading = {
          ConstraintLayout(
            modifier = Modifier.fillMaxSize()
          ) {
            val indicator = createRef()
            CircularProgressIndicator(
              modifier = Modifier.constrainAs(indicator) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
              }
            )
          }
        },
        failure = {
          ConstraintLayout(
            modifier = Modifier.fillMaxSize()
          ) {
            Text(
              text = "image request failed.",
              modifier = Modifier.constrainAs(message) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
              }
            )
          }
        }
      )
      Text(
        text = poster.name,
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Center,
        modifier = Modifier.constrainAs(title) {
          centerHorizontallyTo(parent)
          top.linkTo(image.bottom)
        }.padding(8.dp)
      )
      Text(
        text = poster.playtime,
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center,
        modifier = Modifier.constrainAs(content) {
          centerHorizontallyTo(parent)
          top.linkTo(title.bottom)
        }.padding(horizontal = 8.dp)
          .padding(bottom = 12.dp)
      )
      BalloonAnchor(
        reference = image,
        modifier = Modifier.aspectRatio(0.8f),
        balloon = BalloonUtils.getTitleBalloon(
          context = context,
          title = poster.name,
          lifecycle = lifecycleOwner
        ),
        onAnchorClick = { balloon, anchor -> balloon.showAlignBottom(anchor) }
      )
    }
  }
}

@Preview
@Composable
fun PosterPreviewLight() {
  DisneyComposeTheme(darkTheme = false) {
    Poster(
      poster = MockUtil.getMockPoster()
    )
  }
}

@Preview
@Composable
fun PosterPreviewDark() {
  DisneyComposeTheme(darkTheme = true) {
    Poster(
      poster = MockUtil.getMockPoster()
    )
  }
}
