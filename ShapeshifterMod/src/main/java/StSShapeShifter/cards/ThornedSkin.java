package StSShapeShifter.cards;

import StSShapeShifter.actions.ModifyMagicAction;
import StSShapeShifter.characters.ShapeShifter;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
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
    private static final int BLOCK = 11;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final int UPGRADE_PLUS_MAGIC = 1;
    private int count = 0;

    // /STAT DECLARATION/


    public ThornedSkin() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = 2;
        this.witherValue = this.baseWitherValue = 1;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyWither();
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        this.addToBot(new ModifyBlockAction(this.uuid, -this.witherValue));
        this.addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ModifyMagicAction(this.uuid, -this.witherValue));
        updateBloomCount(-this.witherValue);

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


