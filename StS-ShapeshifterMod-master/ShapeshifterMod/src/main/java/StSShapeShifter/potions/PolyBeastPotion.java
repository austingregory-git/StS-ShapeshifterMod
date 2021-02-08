package StSShapeShifter.potions;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.actions.DiscoverCardAction;
import StSShapeShifter.cards.AbstractShapeShifterCard;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.patches.EnumsPatch;
import StSShapeShifter.util.AllForms;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
import java.util.Random;

public class PolyBeastPotion extends AbstractPotion {


    public static final String POTION_ID = ShapeshifterMod.makeID(PolyBeastPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public PolyBeastPotion() {
        // The bottle shape and inside is determined by potion size and color. The actual colors are the main DefaultMod.java
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.BOLT, PotionColor.SNECKO);
        
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
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for(int i=0; i<potency; i++) {
                ArrayList<AbstractCard> forms = new ArrayList<AbstractCard>(AllForms.getAllFormsCards());
                AbstractCard c = forms.get(new Random().nextInt(forms.size()));
                ShapeshifterMod.logger.info(c.cardID);
                if(c instanceof AbstractShapeShifterCard) {
                    ((AbstractShapeShifterCard) c).ModifiedCostCode = 2;
                }
                c.costForTurn = 0;
                c.exhaust = true;
                AbstractMonster targetMonster = AbstractDungeon.getRandomMonster();
                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, targetMonster));
            }
        }
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new PolyBeastPotion();
    }

    // This is your potency.
    @Override
    public int getPotency(final int potency) {
        return 3;
    }
}
