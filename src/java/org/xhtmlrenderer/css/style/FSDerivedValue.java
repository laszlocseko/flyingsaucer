package org.xhtmlrenderer.css.style;

import org.xhtmlrenderer.css.constants.IdentValue;

import java.awt.*;


/**
 * Marker interface for all derived values. All methods for any
 * possible style are declared here, which doesn't make complete
 * sense, as, for example, a length can't return a value for asColor().
 * This is done so that CalculatedStyle can just look up an
 * FSDerivedValue, without casting, and call the appropriate function
 * without a cast to the appropriate subtype.
 * The users of CalculatedStyle have to then make sure they don't
 * make meaningless calls like asColor(CSSName.HEIGHT). DerivedValue
 * and IdentValue, the two implementations of this interface, just
 * throw a RuntimeException if they can't handle the call.
 */
public interface FSDerivedValue {
    FSDerivedValue copyOf();
    boolean isDeclaredInherit();

    float asFloat();
    Color asColor();
    Point asPoint(
            float parentWidth,
            float parentHeight,
            CssContext ctx
    );

    float getFloatProportionalTo(
            float baseValue,
            CssContext ctx
    );
    String asString();
    String[] asStringArray();
    IdentValue asIdentValue();
    boolean hasAbsoluteUnit();
    boolean isIdent();
}
