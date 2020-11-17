package StSShapeShifter.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractFormPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractFormPower() {
        super();
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, c);
        if (this.img != null) {
            sb.setColor(c);
            sb.draw(this.img, owner.hb.cX - 64.0F, owner.hb.cY + 128.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * 1.5F, Settings.scale * 1.5F, 0.0F, 0, 0, 128, 128, false, false);
        } else {
            sb.setColor(c);
            sb.draw(this.region128, owner.hb.cX - 64.0F, owner.hb.cY + 96.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F);
        }
    }
}
