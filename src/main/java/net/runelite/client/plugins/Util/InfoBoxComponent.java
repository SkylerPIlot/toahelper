package net.runelite.client.plugins.Util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.runelite.client.ui.overlay.components.BackgroundComponent;
import net.runelite.client.ui.overlay.components.LayoutableRenderableEntity;
import net.runelite.client.ui.overlay.components.TextComponent;

@Setter
public class InfoBoxComponent implements LayoutableRenderableEntity
{
    @Getter
    @Setter(AccessLevel.NONE)
    private final Rectangle bounds = new Rectangle();

    private Point preferredLocation = new Point();
    private Dimension preferredSize;

    private Font font;
    private String text;
    private String title;

    private Color textColor = Color.WHITE;
    private Color backgroundColor;

    private BufferedImage image;

    private int textXOffset = -3;
    private int textYOffset = -2;

    private int titleXOffset = 2;

    public Dimension render(final Graphics2D graphics2D)
    {
        if (image == null)
        {
            return null;
        }

        graphics2D.setFont(font);

        final int baseX = preferredLocation.x;
        final int baseY = preferredLocation.y;
        final int size = Math.max(preferredSize.width, preferredSize.height);

        final Rectangle rectangle = new Rectangle(baseX, baseY, size, size);

        new BackgroundComponent(backgroundColor, rectangle, true).render(graphics2D);

        final int imageX = baseX + (size - image.getWidth(null)) / 2;
        final int imageY = baseY + (size - image.getHeight(null)) / 2;

        graphics2D.drawImage(image, imageX, imageY, null);

        final FontMetrics fontMetrics = graphics2D.getFontMetrics();

        if (title != null && !title.isEmpty())
        {
            final TextComponent textComponent = new TextComponent();
            textComponent.setColor(textColor);
            textComponent.setText(title);
            textComponent.setPosition(new Point(baseX + titleXOffset, baseY + fontMetrics.getHeight()));
            textComponent.render(graphics2D);
        }

        if (text != null && !text.isEmpty())
        {
            final TextComponent textComponent = new TextComponent();
            textComponent.setColor(textColor);
            textComponent.setText(text);
            textComponent.setPosition(new Point(baseX + (size - fontMetrics.stringWidth(text)) + textXOffset, baseY + size + textYOffset));
            textComponent.render(graphics2D);
        }

        bounds.setBounds(rectangle);

        return rectangle.getSize();
    }
}
