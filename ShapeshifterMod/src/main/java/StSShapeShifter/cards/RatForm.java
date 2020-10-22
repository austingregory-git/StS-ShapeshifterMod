package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.powers.BearFormPower;
import StSShapeShifter.powers.DodgePower;
import StSShapeShifter.powers.RatFormPower;
import StSShapeShifter.powers.TortoiseFormPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.powers.DexterityPower;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class RatForm extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(RatForm.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;
    //public AbstractPlayer owner = null;
    //public int amount = Integer.MIN_VALUE;
    private static final int COST = 1;
    private static final int UPGRADE_PLUS_MAGIC = 2;
    private static final boolean IS_FORM = true;

    // /STAT DECLARATION/


    public RatForm() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.defaultBaseSecondMagicNumber = 1;
        this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber;

        //this.tags.add(CardTags.STARTER_DEFEND); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, -this.defaultSecondMagicNumber)));
        this.addToBot(new ApplyPowerAction(p, p, new DodgePower(p, this.defaultSecondMagicNumber)));

        if(!p.stance.ID.equals("RatFormStance") && !p.hasPower("CannotChangeStancePower")) {
            //this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
            this.addToBot(new ApplyPowerAction(p, p, new RatFormPower(p, this.magicNumber)));
            this.addToBot(new ChangeStanceAction("RatFormStance"));
        }

        //this.addToBot();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            initializeDescription();
        }
    }
}


