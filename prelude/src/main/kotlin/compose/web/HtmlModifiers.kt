package compose.web

import org.w3c.dom.HTMLElement
import org.w3c.dom.css.CSSStyleDeclaration
import org.w3c.dom.events.EventListener

/**
 * Adds an event listener for the target node which is applied on node recomposition
 */
internal class EventModifier(val eventName: String, val listener: EventListener) : Modifier.Element

/**
 * Adds a css style configuration which would be applied when node is updated
 */
internal class CssModifier(val configure: CSSStyleDeclaration.() -> Unit) : Modifier.Element

/**
 * Updates target node properties on recomposition
 * Properties are not cleared automatically (yet) when modifier is removed
 */
internal class PropertyModifier<R : HTMLElement>(val configure: R.() -> Unit): Modifier.Element

fun Modifier.css(configure: CSSStyleDeclaration.() -> Unit): Modifier =
    this.then(CssModifier(configure))

fun Modifier.event(eventName: String, listener: EventListener) : Modifier =
    this.then(EventModifier(eventName, listener))

fun <R : HTMLElement> Modifier.property(configure: R.() -> Unit) : Modifier =
    this.then(PropertyModifier(configure))