package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.powers.BearFormPower;
import StSShapeShifter.powers.WaspSwarmFormPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.Iterator;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class WaspSwarmForm extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(WaspSwarmForm.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;
    //public AbstractPlayer owner = null;
    //public int amount = Integer.MIN_VALUE;
    private static final int COST = -1;
    private static final int UPGRADE_MAGIC = 1;
    private static final boolean IS_FORM = true;

    // /STAT DECLARATION/


    public WaspSwarmForm() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 0;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        waspSwarmFormEffect(p);
    }

    public void waspSwarmFormEffect(AbstractPlayer p) {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }
        if (effect > 0) {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.flash();
                Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();

                while(var3.hasNext()) {
                    AbstractMonster monster = (AbstractMonster)var3.next();
                    if (!monster.isDead && !monster.isDying) {
                        this.addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, effect + this.magicNumber, false), effect + this.magicNumber));
                    }
                }
            }
            this.addToBot(new ApplyPowerAction(p, p, new WaspSwarmFormPower(p, effect + this.magicNumber)));
            if (!this.freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }
        if(!p.stance.ID.equals("WaspSwarmFormStance") && !p.hasPower("CannotChangeStancePower")) {
            this.addToBot(new ChangeStanceAction("WaspSwarmFormStance"));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
            this.rawDescription = "Upon Entering Wasp Swarm Form, Apply X+1 Weak to all enemies. While in Wasp Swarm Form, deal 2(X+1) damage to all enemies at the start of your turn.";
            initializeDescription();
        }
    }
}


