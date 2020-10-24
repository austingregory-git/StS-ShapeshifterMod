package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.powers.BearFormPower;
import StSShapeShifter.powers.LionFormPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class LionForm extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(LionForm.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;
    //public AbstractPlayer owner = null;
    //public int amount = Integer.MIN_VALUE;
    private static final int COST = 2;
    private static final int UPGRADE_MAGIC = 2;
    private static final boolean IS_FORM = true;

    // /STAT DECLARATION/


    public LionForm() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 3;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.hasPower("Strength")) {
            if(upgraded) {
                int strToAdd = p.getPower("Strength").amount * 2;
                this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, strToAdd)));
            }
            else {
                int strToAdd = p.getPower("Strength").amount;
                this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, strToAdd)));
            }

        }

        if(!p.stance.ID.equals("LionFormStance") && !p.hasPower("CannotChangeStancePower")) {
            this.addToBot(new ApplyPowerAction(p, p, new LionFormPower(p, this.magicNumber)));
            this.addToBot(new ChangeStanceAction("LionFormStance"));
        }

        //this.addToBot();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            //upgradeDefaultSecondMagicNumber(2);
            initializeDescription();
        }
    }
}



