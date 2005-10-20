package org.xhtmlrenderer.css.style.derived;

import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.RGBColor;
import org.xhtmlrenderer.css.constants.CSSName;
import org.xhtmlrenderer.css.style.DerivedValue;
import org.xhtmlrenderer.css.style.FSDerivedValue;
import org.xhtmlrenderer.css.style.CalculatedStyle;
import org.xhtmlrenderer.css.util.ConversionUtil;
import org.xhtmlrenderer.util.XRRuntimeException;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: patrick
 * Date: Oct 17, 2005
 * Time: 2:09:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class ColorValue extends DerivedValue {
    private static final Color COLOR_TRANSPARENT = new Color(0, 0, 0, 0);
    private static final String TRANSPARENT = "transparent";

    private Color _color;

    public ColorValue(
            CalculatedStyle style,
            CSSName name,
            short cssSACUnitType,
            String cssText,
            String cssStringValue,
            RGBColor rgbColor
    ) {
        super(style, name, cssSACUnitType, cssText, cssStringValue);
        _color = deriveColor(rgbColor);
    }

    private ColorValue(
            CalculatedStyle style,
            CSSName name,
            short cssSACUnitType,
            String cssText,
            String cssStringValue,
            Color color
    ) {
        super(style, name, cssSACUnitType, cssText, cssStringValue);
        _color = color;
    }

    public FSDerivedValue copyOf() {
        return new ColorValue(getStyle(), getCssName(), getCssSacUnitType(), getStringValue(), getStringValue(), _color);
    }

    /**
     * Returns the value as a Color, if it is a color.
     *
     * @return The rGBColorValue value
     */
    public Color asColor() {
        return _color;
    }

    private Color deriveColor(RGBColor rgbColor) {
        Color color = null;
        String str = getStringValue();
        try {
            if (TRANSPARENT.equals(str)) {
                color = COLOR_TRANSPARENT;
            } else if (getCssSacUnitType() == CSSPrimitiveValue.CSS_RGBCOLOR && rgbColor != null) {
                color = ConversionUtil.rgbToColor(rgbColor);
            } else {
                color = Color.decode(str);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new XRRuntimeException("Could not return '" + getCssName() + "' in a DerivedValue as a Color (value '" + str + "'). ", ex);
        }
        return color;
    }
}
