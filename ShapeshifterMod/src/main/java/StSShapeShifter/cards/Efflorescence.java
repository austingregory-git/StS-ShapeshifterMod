package StSShapeShifter.cards;

import StSShapeShifter.actions.ModifyMagicAction;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.util.BloomCountUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.common.ModifyExhaustiveAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.characters.TheDefault;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class Efflorescence extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(Efflorescence.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 2;
    private int count = 0;

    // /STAT DECLARATION/


    public Efflorescence() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 2;
        //this.defaultBaseSecondMagicNumber = 1;
        this.growValue = this.baseGrowValue = 1;
        this.magicNumber = this.baseMagicNumber;
    }



    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyGrow();
        this.addToBot(new GainEnergyAction(this.magicNumber));
        this.addToBot(new ModifyMagicAction(this.uuid, this.growValue));
        updateBloomCount(this.growValue);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeGrowValue(1);
            //this.upgradeDefaultSecondMagicNumber(1);
            //this.rawDescription = "Gain !M! Energy. [#32CD32] Grow[] 2";
            initializeDescription();
        }
    }
}

