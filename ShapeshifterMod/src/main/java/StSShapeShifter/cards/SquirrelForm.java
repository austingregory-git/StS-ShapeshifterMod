package StSShapeShifter.cards;

import StSShapeShifter.cards.tempCards.Acorn;
import StSShapeShifter.cards.tempCards.Banana;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.powers.BearFormPower;
import StSShapeShifter.powers.LionFormPower;
import StSShapeShifter.powers.MonkeyFormPower;
import StSShapeShifter.powers.SquirrelFormPower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class SquirrelForm extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(SquirrelForm.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;
    //public AbstractPlayer owner = null;
    //public int amount = Integer.MIN_VALUE;
    private static final int COST = 0;
    private static final int UPGRADE_MAGIC = 1;
    private static final boolean IS_FORM = true;

    // /STAT DECLARATION/


    public SquirrelForm() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //p.relics.add(AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.COMMON));
        //this.addToBot(new RelicAboveCreatureAction(p, AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.COMMON)));
        //this.addToBot(new GainGoldAction(4));
        //int roll = AbstractDungeon.treasureRng.random(0, 99);
        //this.addToBot(new RollMoveAction(m));
        this.addToBot(new MakeTempCardInHandAction(new Acorn()));
        this.addToBot(new MakeTempCardInHandAction(new Acorn()));

        if(!p.stance.ID.equals("SquirrelFormStance") && !p.hasPower("CannotChangeStancePower")) {
            this.addToBot(new ApplyPowerAction(p, p, new SquirrelFormPower(p)));
            this.addToBot(new ChangeStanceAction("SquirrelFormStance"));
        }

        //this.addToBot();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeMagicNumber(UPGRADE_MAGIC);
            //upgradeDefaultSecondMagicNumber(2);
            this.rawDescription = "Upon entering Squirrel Form, add 2 upgraded Acorns to your hand. While in Squirrel Form, add an upgraded Acorn to your hand at the start of your turn.";
            initializeDescription();
        }
    }
}





