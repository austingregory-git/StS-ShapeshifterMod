package StSShapeShifter.cards;

import StSShapeShifter.cards.tempCards.Banana;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.powers.BearFormPower;
import StSShapeShifter.powers.FoxFormPower;
import StSShapeShifter.powers.LionFormPower;
import StSShapeShifter.powers.MonkeyFormPower;
import StSShapeShifter.stances.FoxFormStance;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.CannotChangeStancePower;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class FoxForm extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(FoxForm.class.getSimpleName());
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
    private static final int DAMAGE = 9;
    private static final int UPGRADE_PLUS_DAMAGE = 3;
    private static final boolean IS_FORM = true;

    // /STAT DECLARATION/


    public FoxForm() {
        super(ID, ShapeshifterMod.imgFromId(ID), COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageRandomEnemyAction(new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        if(!p.stance.ID.equals(FoxFormStance.STANCE_ID) && !p.hasPower(CannotChangeStancePower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new FoxFormPower(p, p, upgraded)));
            this.addToBot(new ChangeStanceAction(FoxFormStance.STANCE_ID));
            CardCrawlGame.sound.play(ShapeshifterMod.makeID("SFX_FlamingoForm"));

        }

        //this.addToBot();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            initializeDescription();
        }
    }
}




