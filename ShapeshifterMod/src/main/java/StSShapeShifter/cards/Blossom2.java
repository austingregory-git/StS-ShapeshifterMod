package StSShapeShifter.cards;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.actions.ModifyGrowAction;
import StSShapeShifter.characters.ShapeShifter;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Iterator;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class Blossom2 extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(Blossom2.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 3;
    private static final int UPGRADE_BASE_COST = 2;

    private int count = 0;

    // /STAT DECLARATION/


    public Blossom2() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator var1 = AbstractDungeon.player.drawPile.group.iterator();
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();
        Iterator var3 = AbstractDungeon.player.discardPile.group.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            if(c instanceof AbstractDynamicCard && ((AbstractDynamicCard) c).growValue > 0) {
                ((AbstractDynamicCard) c).simulateGrow(this.magicNumber);
                ((AbstractDynamicCard) c).applyGrow();
            }
        }
        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard) var2.next();
            if(c instanceof AbstractDynamicCard && ((AbstractDynamicCard) c).growValue > 0) {
                ((AbstractDynamicCard) c).simulateGrow(this.magicNumber);
                ((AbstractDynamicCard) c).applyGrow();
            }
        }
        while(var3.hasNext()) {
            AbstractCard c = (AbstractCard) var3.next();
            if(c instanceof AbstractDynamicCard && ((AbstractDynamicCard) c).growValue > 0) {
                ((AbstractDynamicCard) c).simulateGrow(this.magicNumber);
                ((AbstractDynamicCard) c).applyGrow();
            }
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}


