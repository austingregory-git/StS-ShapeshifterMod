package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.powers.BearFormPower;
import StSShapeShifter.stances.BearFormStance;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.watcher.CannotChangeStancePower;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class BearForm extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(BearForm.class.getSimpleName());
    public static final String IMG = makeCardPath("card-art-generated/BearForm.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;
    //public AbstractPlayer owner = null;
    //public int amount = Integer.MIN_VALUE;
    private static final int COST = 2;
    private static final boolean IS_FORM = true;

    // /STAT DECLARATION/


    public BearForm() {
        super(ID, ShapeshifterMod.imgFromId(ID), COST, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 8;
        this.defaultBaseSecondMagicNumber = 4;
        this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new GainBlockAction(p, block));
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));

        if(!p.stance.ID.equals(BearFormStance.STANCE_ID) && !p.hasPower(CannotChangeStancePower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new BearFormPower(p, this.defaultSecondMagicNumber)));
            this.addToBot(new ChangeStanceAction(BearFormStance.STANCE_ID));
            CardCrawlGame.sound.play(ShapeshifterMod.makeID("SFX_BearForm"));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeDefaultSecondMagicNumber(2);
            initializeDescription();
        }
    }
}


