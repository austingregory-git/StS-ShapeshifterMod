package StSShapeShifter.cards;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.tempCards.Star;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.powers.PlayerFlightPower;
import StSShapeShifter.powers.StormGryphonFormPower;
import StSShapeShifter.powers.UnicornFormPower;
import StSShapeShifter.stances.StormGryphonFormStance;
import StSShapeShifter.stances.UnicornFormStance;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.CannotChangeStancePower;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class StormGryphonForm extends AbstractGrowCard {

    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(StormGryphonForm.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;
    private static final int COST = 3;
    private static final int UPGRADE_MAGIC = 1;
    private static final int UPGRADE_MAGIC2 = 1;
    private static final int UPGRADE_GROW = 1;
    private static final boolean IS_FORM = true;

    // /STAT DECLARATION/


    public StormGryphonForm() {
        super(ID, ShapeshifterMod.imgFromId(ID), COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber = 4;
        this.growValue = this.baseGrowValue = 3;
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PlayerFlightPower(p, this.magicNumber)));

        if(!p.stance.ID.equals(StormGryphonFormStance.STANCE_ID) && !p.hasPower(CannotChangeStancePower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new StormGryphonFormPower(p, this.defaultSecondMagicNumber, this.growValue)));
            this.addToBot(new ChangeStanceAction(StormGryphonFormStance.STANCE_ID));
            CardCrawlGame.sound.play(ShapeshifterMod.makeID("SFX_StormGryphonForm"));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
            upgradeDefaultSecondMagicNumber(UPGRADE_MAGIC2);
            upgradeDefaultSecondMagicNumber(UPGRADE_GROW);
            initializeDescription();
        }
    }
}



