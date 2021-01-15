package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.powers.BearFormPower;
import StSShapeShifter.powers.WaspSwarmFormPower;
import StSShapeShifter.stances.WaspSwarmFormStance;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.CannotChangeStancePower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.Iterator;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class WaspSwarmForm extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(WaspSwarmForm.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;
    //public AbstractPlayer owner = null;
    //public int amount = Integer.MIN_VALUE;
    private static final int COST = -1;
    private static final boolean IS_FORM = true;

    // /STAT DECLARATION/


    public WaspSwarmForm() {
        super(ID, ShapeshifterMod.imgFromId(ID), COST, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        waspSwarmFormEffect(p);
    }

    public void waspSwarmFormEffect(AbstractPlayer p) {
        this.ModifiedCostCode = 2;
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }

        if(upgraded) {
            effect++;
        }
        if (effect > 0) {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.flash();
                Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();

                while(var3.hasNext()) {
                    AbstractMonster monster = (AbstractMonster)var3.next();
                    if (!monster.isDead && !monster.isDying) {
                        this.addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, effect, false), effect));
                        this.addToBot(new ApplyPowerAction(monster, p, new PoisonPower(monster, p, effect), effect));
                    }
                }
            }
            this.addToBot(new ApplyPowerAction(p, p, new WaspSwarmFormPower(p, effect)));
            if (!this.freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }
        if(!p.stance.ID.equals(WaspSwarmFormStance.STANCE_ID) && !p.hasPower(CannotChangeStancePower.POWER_ID)) {
            this.addToBot(new ChangeStanceAction(WaspSwarmFormStance.STANCE_ID));
            CardCrawlGame.sound.play(ShapeshifterMod.makeID("SFX_WaspSwarmForm"));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //this.rawDescription = "Upon Entering Wasp Swarm Form, Apply X+1 Weak and Poison to all enemies. While in Wasp Swarm Form, deal 2(X+1) damage to all enemies at the start of your turn.";
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}


