
<h1 align="center">Orchestra</h1></br>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/skydoves"><img alt="Profile" src="https://skydoves.github.io/badges/skydoves.svg"/></a> 
</p>

<p align="center">
🎺 Jetpack Compose compatible libraries using Balloon, ColorPickerView, PowerSpinner.
</p>
<p align="center">
<img src="https://user-images.githubusercontent.com/24237865/61194943-f9d70380-a6ff-11e9-807f-ba1ca8126f8a.gif" width="32%"/>
<img src="https://user-images.githubusercontent.com/24237865/95007367-d58b7d80-0649-11eb-857b-9e0187be70d1.gif" width="30.4%"/>
<img src="https://user-images.githubusercontent.com/24237865/71962685-534a6600-323d-11ea-9e1e-df1f68cb2181.gif" width="32%"/>
</p>

## Balloon
[![Download](https://api.bintray.com/packages/devmagician/maven/orchestra-balloon/images/download.svg) ](https://bintray.com/devmagician/maven/orchestra-balloon/_latestVersion) <br>
And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
    implementation "com.github.skydoves:orchestra-balloon:1.0.0"
}
```

<img src="https://user-images.githubusercontent.com/24237865/95007576-3caa3180-064c-11eb-815b-b995f45bb18a.gif" align="right" width="32%"/>

### Usage
`BalloonAnchor` composable can be used in `ConstraintLayout` and it receives a constraint reference. In the below, `BalloonAnchor` references `image` composable. When clicked the `image` composable, balloon popup will be shown.
```kotlin
ConstraintLayout {
  val (image, title, content, message) = createRefs()

  // image, title, content, message //
  
  BalloonAnchor(
    reference = image,
    modifier = Modifier.aspectRatio(0.8f),
    balloon = BalloonUtils.getTitleBalloon(
      context = context,
      title = poster.name,
      lifecycle = lifecycleOwner
    ),
    onAnchorClick = { balloon, anchor -> balloon.show(anchor) },
    onClickIndication = RippleIndication(color = purple500)
  )
```
Or we can create a `BalloonAnchor` composable using [Balloon.Factory](https://github.com/skydoves/balloon#lazy-initialization).
```kotlin
BalloonAnchor(
  reference = image,
  modifier = Modifier.aspectRatio(0.8f),
  factory = MyBalloonFactory::class,
  onAnchorClick = { balloon, anchor -> balloon.show(anchor) },
  onBalloonClick = { },
  onBalloonDismiss = { },
  onBalloonInitialized = { content -> },
  onBalloonOutsideTouch = { content, event -> },
  onClickIndication = RippleIndication(color = purple500)
)
```

## ColorPicker
[![Download](https://api.bintray.com/packages/devmagician/maven/orchestra-colorpicker/images/download.svg) ](https://bintray.com/devmagician/maven/orchestra-colorpicker/_latestVersion)<br>
And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
    implementation "com.github.skydoves:orchestra-colorpicker:$version"
}
```

<img src="https://user-images.githubusercontent.com/24237865/95007577-416ee580-064c-11eb-92b7-ad30c5b36da0.gif" align="right" width="32%"/>

### Usage
`ColorPicker` composable implements a color picker with `AlphaSlideBar` and `BrightnessSlideBar`. We can create an alpha sidebar and brightness sidebar for changing saturation and lightness by tapping on the desired color. They should be used in `children` inner composable in `ColorPicker`, and they receive a `colorPickerView` as a parameter.
```kotlin
val (selectedColor, setSelectedColor) = remember { mutableStateOf(ColorEnvelope(0)) }
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
```

### AlphaTileBox
In a normal View, it can not represent ARGB colors accurately. Because a color will be mixed with the parent's background-color. For resolving it we can use `AlphaTileBox` composable. `AlphaTileBox` composable reflects ARGB colors.
```kotlin
AlphaTileBox(
  modifier = modifier.constrainAs(square) {
    bottom.linkTo(parent.bottom)
    centerHorizontallyTo(parent)
  }.size(64.dp).clip(RoundedCornerShape(4.dp))
) {
  it.setBackgroundColor(selectedColor.color)
}
```

## Spinner
[![Download](https://api.bintray.com/packages/devmagician/maven/orchestra-spinner/images/download.svg) ](https://bintray.com/devmagician/maven/orchestra-spinner/_latestVersion) <br>
And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
    implementation "com.github.skydoves:orchestra-spinner:$version"
}
```

<img src="https://user-images.githubusercontent.com/24237865/95007578-42a01280-064c-11eb-9081-e5567274c9ba.gif" align="right" width="32%"/>

### Usage
`Spinner` composable implements a lightweight dropdown popup spinner. Here is an example for creating a spinner using a sting array resource. We should use the String generic type for creating a spinner when we us a string array resource.

```kotlin
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
```
Here is an another example using a List for creating a spinner. In this case, we don't need to decide a generic type of Spinner.

```kotlin
 val coffeeList = remember { listOf("Americano", "Cold Brew", "Espresso", "Latte") }
 val (selectedItem1, setSelectedItem1) = remember { mutableStateOf("Choose your coffee") }
 Spinner(
      text = selectedItem1,
      modifier = Modifier.fillMaxWidth().padding(16.dp)
        .background(amber700)
        .align(Alignment.CenterHorizontally),
      itemList = coffeeList,
      color = Color.White,
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
```

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/skydoves/Orchestra/stargazers)__ for this repository. :star: <br>
And __[follow](https://github.com/skydoves)__ me for my next creations! 🤩

# License
```xml
Designed and developed by 2020 skydoves (Jaewoong Eum)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
