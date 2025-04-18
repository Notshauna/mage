package mage.cards.m;

import mage.Mana;
import mage.abilities.hint.ConditionHint;
import mage.abilities.hint.ConditionTrueHint;
import mage.abilities.hint.ValueConditionHint;
import mage.abilities.triggers.BeginningOfDrawTriggeredAbility;
import mage.abilities.triggers.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.SourceTappedCondition;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.DamageControllerEffect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.DontUntapInControllersUntapStepSourceEffect;
import mage.abilities.effects.common.UntapSourceEffect;
import mage.abilities.mana.SimpleManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;

import java.util.UUID;

/**
 * @author LevelX2
 */
public final class ManaVault extends CardImpl {

    public ManaVault(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{1}");

        // Mana Vault doesn't untap during your untap step.
        this.addAbility(new SimpleStaticAbility(new DontUntapInControllersUntapStepSourceEffect()));

        // At the beginning of your upkeep, you may pay {4}. If you do, untap Mana Vault.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(
                new DoIfCostPaid(new UntapSourceEffect(), new GenericManaCost(4), "Pay {4} to untap {this}?")
                        .withChooseHint(new ConditionHint(SourceTappedCondition.UNTAPPED))
        ));

        // At the beginning of your draw step, if Mana Vault is tapped, it deals 1 damage to you.
        this.addAbility(new BeginningOfDrawTriggeredAbility(new DamageControllerEffect(1, "it"),
                false).withInterveningIf(SourceTappedCondition.TAPPED));

        // {T}: Add {C}{C}{C}.
        this.addAbility(new SimpleManaAbility(Zone.BATTLEFIELD, Mana.ColorlessMana(3), new TapSourceCost()));
    }

    private ManaVault(final ManaVault card) {
        super(card);
    }

    @Override
    public ManaVault copy() {
        return new ManaVault(this);
    }
}
