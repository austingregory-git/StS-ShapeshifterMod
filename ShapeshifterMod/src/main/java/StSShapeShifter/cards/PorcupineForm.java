package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.powers.BearFormPower;
import StSShapeShifter.powers.PorcupineFormPower;
import StSShapeShifter.powers.TortoiseFormPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class PorcupineForm extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(PorcupineForm.class.getSimpleName());
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
    private static final int BLOCK = 8;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final boolean IS_FORM = true;

    // /STAT DECLARATION/


    public PorcupineForm() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;

        //this.tags.add(CardTags.STARTER_DEFEND); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!p.stance.ID.equals("PorcupineFormStance") && !p.hasPower("CannotChangeStancePower")) {
            this.addToBot(new ChangeStanceAction("PorcupineFormStance"));
            this.addToBot(new ApplyPowerAction(p, p, new PorcupineFormPower(p, this.magicNumber)));
        }
        else {
            this.addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, this.magicNumber)));
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


