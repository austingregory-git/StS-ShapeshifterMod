package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.characters.TheDefault;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class ThornedSkin extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(ThornedSkin.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 2;
    private static final int BLOCK = 8;
    private static final int UPGRADE_PLUS_BLOCK = 4;
    private static final int UPGRADE_PLUS_MAGIC = 1;
    private int count = 0;

    // /STAT DECLARATION/


    public ThornedSkin() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.defaultBaseSecondMagicNumber = 1;
        this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber;

        //this.tags.add(CardTags.STARTER_DEFEND); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int thornsValue = this.magicNumber - count;
        if(thornsValue < 0)
            thornsValue = 0;
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        if(this.baseBlock > 0)
            this.addToBot(new ModifyBlockAction(this.uuid, -this.defaultSecondMagicNumber));

        this.addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, thornsValue), thornsValue));
        count++;
        if(this.baseMagicNumber > 0)
            this.baseMagicNumber = this.magicNumber - count;
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            initializeDescription();
        }
    }
}


