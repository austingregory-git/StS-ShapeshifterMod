package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
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
    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private int count = 0;

    // /STAT DECLARATION/


    public Efflorescence() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 2;
        this.defaultBaseSecondMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber;


        //this.tags.add(CardTags.STARTER_DEFEND); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
    }

    // Actions the card should do.
    //TODO update grow value when upgraded
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        count++;
        this.addToBot(new GainEnergyAction(this.magicNumber + this.defaultSecondMagicNumber - count));
        this.defaultSecondMagicNumber += defaultBaseSecondMagicNumber;
        this.baseMagicNumber = this.magicNumber + this.defaultSecondMagicNumber - count;
        //this.baseMagicNumber = this.defaultSecondMagicNumber;
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeDefaultSecondMagicNumber(1);
            //upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}

