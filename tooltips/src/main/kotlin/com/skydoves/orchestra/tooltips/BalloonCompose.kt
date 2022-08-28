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

package com.skydoves.orchestra.tooltips

import android.content.Context
import android.view.MotionEvent
import android.view.View
import androidx.compose.foundation.combinedClickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.lifecycle.LifecycleOwner
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.OnBalloonInitializedListener
import kotlin.reflect.KClass

/**
 * An anchor of the Balloon that should be referenced for showing Balloon popup.
 * Receives an instance of a Balloon.Factory class.
 *
 * This composable only works in ConstraintLayout,
 * and must be placed in the below the reference composable.
 *
 * ```
 * BalloonAnchor(
 *   reference = image,
 *   modifier = Modifier.aspectRatio(0.8f),
 *   factory = MyBalloonFactory::class,
 *   onAnchorClick = { tooltips, anchor -> tooltips.show(anchor) },
 *   onBalloonClick = { },
 *   onBalloonDismiss = { },
 *   onBalloonInitialized = { content -> },
 *   onBalloonOutsideTouch = { content, event -> }
 * )
 * ```
 */
@Composable
public fun <T : Balloon.Factory> ConstraintLayoutScope.BalloonAnchor(
    reference: ConstrainedLayoutReference,
    modifier: Modifier = Modifier,
    factory: KClass<T>,
    context: Context = LocalContext.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    anchor: View = remember { View(context) },
    onAnchorClick: (Balloon, View) -> Unit = { _, _ -> },
    onAnchorLongClick: (Balloon, View) -> Unit = { _, _ -> },
    onBalloonClick: (View) -> Unit = { _ -> },
    onBalloonDismiss: () -> Unit = {},
    onBalloonInitialized: (View) -> Unit = { _ -> },
    onBalloonOutsideTouch: (View, MotionEvent) -> Unit = { _, _ -> },
    onClickEnabled: Boolean = true,
    onClickLabel: String? = null,
    onLongClickLabel: String? = null,
    role: Role? = null,
    update: (Balloon, View) -> Unit = { _, _ -> }
) {
    BalloonAnchor(
        modifier = modifier.apply {
            constrainAs(ConstrainedLayoutReference(anchor.id)) {
                start.linkTo(reference.start)
                end.linkTo(reference.end)
                top.linkTo(reference.top)
                bottom.linkTo(reference.bottom)
            }
        },
        factory = factory,
        context = context,
        lifecycleOwner = lifecycleOwner,
        anchor = anchor,
        onAnchorClick = onAnchorClick,
        onAnchorLongClick = onAnchorLongClick,
        onBalloonClick = onBalloonClick,
        onBalloonDismiss = onBalloonDismiss,
        onBalloonInitialized = onBalloonInitialized,
        onBalloonOutsideTouch = onBalloonOutsideTouch,
        onClickEnabled = onClickEnabled,
        onClickLabel = onClickLabel,
        onLongClickLabel = onLongClickLabel,
        role = role,
        update = update
    )
}

/**
 * An anchor of the Balloon that should be referenced for showing Balloon popup.
 * Receives an instance of a Balloon.
 *
 * This composable only works in ConstraintLayout,
 * and must be placed in the below the reference composable.
 *
 * ```
 * BalloonAnchor(
 *   reference = image,
 *   modifier = Modifier.aspectRatio(0.8f),
 *   tooltips = balloonUtils.getTitleBalloon(
 *     title = poster.name,
 *     context = context,
 *     lifecycle = lifecycleOwner),
 *   onAnchorClick = { tooltips, anchor -> tooltips.show(anchor) },
 *   onBalloonClick = { },
 *   onBalloonDismiss = { },
 *   onBalloonInitialized = { content -> },
 *   onBalloonOutsideTouch = { content, event -> }
 * )
 * ```
 */
@Composable
public fun ConstraintLayoutScope.BalloonAnchor(
    reference: ConstrainedLayoutReference,
    modifier: Modifier = Modifier,
    balloon: Balloon,
    context: Context = LocalContext.current,
    anchor: View = remember { View(context) },
    onAnchorClick: (Balloon, View) -> Unit = { _, _ -> },
    onAnchorLongClick: (Balloon, View) -> Unit = { _, _ -> },
    onBalloonClick: (View) -> Unit = { _ -> },
    onBalloonDismiss: () -> Unit = {},
    onBalloonInitialized: (View) -> Unit = { _ -> },
    onBalloonOutsideTouch: (View, MotionEvent) -> Unit = { _, _ -> },
    onClickEnabled: Boolean = true,
    onClickLabel: String? = null,
    onLongClickLabel: String? = null,
    role: Role? = null,
    update: (Balloon, View) -> Unit = { _, _ -> }
) {
    BalloonAnchor(
        modifier = modifier.apply {
            constrainAs(ConstrainedLayoutReference(anchor.id)) {
                start.linkTo(reference.start)
                end.linkTo(reference.end)
                top.linkTo(reference.top)
                bottom.linkTo(reference.bottom)
            }
        },
        balloon = balloon,
        context = context,
        anchor = anchor,
        onAnchorClick = onAnchorClick,
        onAnchorLongClick = onAnchorLongClick,
        onBalloonClick = onBalloonClick,
        onBalloonDismiss = onBalloonDismiss,
        onBalloonInitialized = onBalloonInitialized,
        onBalloonOutsideTouch = onBalloonOutsideTouch,
        onClickEnabled = onClickEnabled,
        onClickLabel = onClickLabel,
        onLongClickLabel = onLongClickLabel,
        role = role,
        update = update
    )
}

/**
 * An anchor of the Balloon that should be referenced for showing Balloon popup.
 * Receives an instance of a Balloon.Factory class.
 *
 * ```
 * BalloonAnchor(
 *   modifier = Modifier.aspectRatio(0.8f),
 *   factory = MyBalloonFactory::class,
 *   onAnchorClick = { tooltips, anchor -> tooltips.show(anchor) },
 *   onBalloonClick = { },
 *   onBalloonDismiss = { },
 *   onBalloonInitialized = { content -> },
 *   onBalloonOutsideTouch = { content, event -> }
 * )
 * ```
 */
@Composable
public fun <T : Balloon.Factory> BalloonAnchor(
    modifier: Modifier = Modifier,
    factory: KClass<T>,
    context: Context = LocalContext.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    anchor: View = remember { View(context) },
    onAnchorClick: (Balloon, View) -> Unit = { _, _ -> },
    onAnchorLongClick: (Balloon, View) -> Unit = { _, _ -> },
    onBalloonClick: (View) -> Unit = { _ -> },
    onBalloonDismiss: () -> Unit = {},
    onBalloonInitialized: (View) -> Unit = { _ -> },
    onBalloonOutsideTouch: (View, MotionEvent) -> Unit = { _, _ -> },
    onClickEnabled: Boolean = true,
    onClickLabel: String? = null,
    onLongClickLabel: String? = null,
    role: Role? = null,
    update: (Balloon, View) -> Unit = { _, _ -> }
) {
    val instance: T = remember { factory::java.get().newInstance() }
    val balloon: Balloon = remember { instance.create(context, lifecycleOwner) }
    BalloonAnchor(
        modifier = modifier,
        balloon = balloon,
        context = context,
        anchor = anchor,
        onAnchorClick = onAnchorClick,
        onAnchorLongClick = onAnchorLongClick,
        onBalloonClick = onBalloonClick,
        onBalloonDismiss = onBalloonDismiss,
        onBalloonInitialized = onBalloonInitialized,
        onBalloonOutsideTouch = onBalloonOutsideTouch,
        onClickEnabled = onClickEnabled,
        onClickLabel = onClickLabel,
        onLongClickLabel = onLongClickLabel,
        role = role,
        update = update
    )
}

/*
 * Create an anchor composable of the Balloon that should be referenced for showing Balloon popup.
 * Receives an instance of a Balloon.
 *
 * @see https://github.com/skydoves/balloon
 *
 * ```
 * BalloonAnchor(
 *   modifier = Modifier.aspectRatio(0.8f),
 *   tooltips = balloonUtils.getTitleBalloon(
 *     title = poster.name,
 *     context = context,
 *     lifecycle = lifecycleOwner),
 *   onAnchorClick = { tooltips, anchor -> tooltips.show(anchor) },
 *   onBalloonClick = { },
 *   onBalloonDismiss = { },
 *   onBalloonInitialized = { content -> },
 *   onBalloonOutsideTouch = { content, event -> },
 * )
 * ```
 */
@Composable
public fun BalloonAnchor(
    modifier: Modifier = Modifier,
    balloon: Balloon,
    context: Context = LocalContext.current,
    anchor: View = remember { View(context) },
    onAnchorClick: (Balloon, View) -> Unit = { _, _ -> },
    onAnchorLongClick: (Balloon, View) -> Unit = { _, _ -> },
    onBalloonClick: (View) -> Unit = { _ -> },
    onBalloonDismiss: () -> Unit = {},
    onBalloonInitialized: (View) -> Unit = { _ -> },
    onBalloonOutsideTouch: (View, MotionEvent) -> Unit = { _, _ -> },
    onClickEnabled: Boolean = true,
    onClickLabel: String? = null,
    onLongClickLabel: String? = null,
    role: Role? = null,
    update: (Balloon, View) -> Unit = { _, _ -> }
) {
    // initialize Balloon.
    balloon.setOnBalloonClickListener { onBalloonClick(it) }
    balloon.setOnBalloonDismissListener { onBalloonDismiss() }
    balloon.onBalloonInitializedListener = OnBalloonInitializedListener { onBalloonInitialized(it) }
    balloon.setOnBalloonOutsideTouchListener { view, motionEvent ->
        onBalloonOutsideTouch(view, motionEvent)
    }

    // draw anchor of the Balloon and updates.
    AndroidView(
        factory = { anchor },
        modifier = Modifier
            .combinedClickable(
                enabled = onClickEnabled,
                onClick = { onAnchorClick(balloon, anchor) },
                onLongClick = { onAnchorLongClick(balloon, anchor) },
                role = role,
                onClickLabel = onClickLabel,
                onLongClickLabel = onLongClickLabel
            )
            .then(modifier)
    ) {
        update(balloon, it)
    }
}
