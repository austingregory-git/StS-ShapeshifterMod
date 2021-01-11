package StSShapeShifter.potions;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.actions.DiscoverCardAction;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.patches.EnumsPatch;
import StSShapeShifter.powers.GreenSmothiePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class GreenSmothie extends AbstractPotion {


    public static final String POTION_ID = ShapeshifterMod.makeID(GreenSmothie.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public GreenSmothie() {
        // The bottle shape and inside is determined by potion size and color. The actual colors are the main DefaultMod.java
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.FAIRY, PotionColor.GREEN);
        
        // Potency is the damage/magic number equivalent of potions.
        potency = getPotency();
        
        // Initialize the Description
        description = DESCRIPTIONS[0];
        
       // Do you throw this potion at an enemy or do you just consume it.
        isThrown = false;
        
        // Initialize the on-hover name + description
        tips.add(new PowerTip(name, description));
        
    }

    @Override
    public void use(AbstractCreature target) {
        target = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new ApplyPowerAction(target, target, new GreenSmothiePower(potency)));
        }
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new GreenSmothie();
    }

    // This is your potency.
    @Override
    public int getPotency(final int potency) {
        return 1;
    }
}
