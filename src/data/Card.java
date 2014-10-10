package data;

import materials.Player;
import events.CardBehavior;

public enum Card {
	
	// Aktionskarten
	ANWALT(
		CardType.ACTION,
		"assets/cards/anwalt.png", 
		"assets/cards/back.png", 
		60, 0, 0
	),
	
	AUFERSTEHUNG(
		CardType.ACTION, 
		"assets/cards/auferstehung.png", 
		"assets/cards/back.png", 
		25, 0, 0
	),
	
	AUSGELUTSCHT(
		CardType.ACTION, 
		"assets/cards/ausgelutscht.png", 
		"assets/cards/back.png", 
		30, 0, 0
	),
	
	BERSERKER(
		CardType.ACTION, 
		"assets/cards/berserker.png", 
		"assets/cards/back.png", 
		47, 0, 0,
		
		new CardBehavior() {
			public void targetCard(Player owner, Player targetPlayer, Card targetCard) {
				targetPlayer.removeCard(targetCard);
				owner.removeCard(_card);
			}
		}
	),
	
	BIN_NOCH_NICHT_FERTIG(
		CardType.ACTION, 
		"assets/cards/bin-noch-nicht-fertig.png", 
		"assets/cards/back.png", 
		5, 0, 0
	),
	
	DEFLATION(
		CardType.ACTION, 
		"assets/cards/deflation.png", 
		"assets/cards/back.png", 
		2, 0, 0
	),
	
	DU_BIST_RAUS(
		CardType.ACTION, 
		"assets/cards/du-bist-raus.png", 
		"assets/cards/back.png", 
		20, 0, 0
	),
	
	HANS_IM_GLUECK(
		CardType.ACTION, 
		"assets/cards/hans-im-glueck.png", 
		"assets/cards/back.png", 
		15, 0, 0
	),
	
	INVENTUR(
		CardType.ACTION, 
		"assets/cards/inventur.png", 
		"assets/cards/back.png", 
		2, 0, 0
	),
	
	KUHHANDEL(
		CardType.ACTION, 
		"assets/cards/kuhhandel.png", 
		"assets/cards/back.png", 
		40, 0, 0
	),
	
	MANIPULATION(
		CardType.ACTION, 
		"assets/cards/manipulation.png", 
		"assets/cards/back.png", 
		25, 0, 0
	),
	
	MEHR_BRUTTO_VOM_NETTO(
		CardType.ACTION, 
		"assets/cards/mehr-brutto-vom-netto.png", 
		"assets/cards/back.png", 
		5, 0, 0
	),
	
	MURPHYS_GESETZ(
		CardType.ACTION, 
		"assets/cards/murphys-gesetz.png", 
		"assets/cards/back.png", 
		26, 0, 0
	),
	
	SABOTAGE(
		CardType.ACTION, 
		"assets/cards/sabotage.png", 
		"assets/cards/back.png", 
		43, 0, 0
	),
	
	SONDERANGEBOT(
		CardType.ACTION, 
		"assets/cards/sonderangebot.png", 
		"assets/cards/back.png", 
		21, 0, 0
	),
	
	SPENDENAKTION(
		CardType.ACTION, 
		"assets/cards/spendenaktion.png", 
		"assets/cards/back.png", 
		3, 0, 0
	),
	
	STEUEREINTREIBER(
		CardType.ACTION, 
		"assets/cards/steuereintreiber.png", 
		"assets/cards/back.png", 
		5, 0, 0
	),
	
	STREIK(
		CardType.ACTION, 
		"assets/cards/streik.png", 
		"assets/cards/back.png", 
		10, 0, 0
	),
	
	WIRTSCHAFTSWUNDER(
		CardType.ACTION, 
		"assets/cards/wirtschaftswunder.png", 
		"assets/cards/back.png", 
		12, 0, 0
	),
	
	ZWANGSVERKAUF(
		CardType.ACTION, 
		"assets/cards/zwangsverkauf.png", 
		"assets/cards/back.png", 
		25, 0, 0
	),
	
	// Statussymbole
	ARMBANDUHR(
		CardType.WEALTH, 
		"assets/cards/armbanduhr.png", 
		"assets/cards/back.png", 
		16, 4, 0
	),
	
	DIADEM(
		CardType.WEALTH, 
		"assets/cards/diadem.png", 
		"assets/cards/back.png", 
		20, 5, 0
	),
	
	GOLDENES_KLOSETT(
		CardType.WEALTH, 
		"assets/cards/goldenes-klosett.png", 
		"assets/cards/back.png", 
		40, 10, 0
	),
	
	GROSSGRUNDBESITZ(
		CardType.WEALTH, 
		"assets/cards/grossgrundbesitz.png", 
		"assets/cards/back.png", 
		29, 0, 0,
		
		new CardBehavior() {
			public void update(Player owner) {
				owner.raiseStatus(owner.getCards().size());
			}
		}
	),
	
	HEXENHAMMER(
		CardType.WEALTH, 
		"assets/cards/hexenhammer.png", 
		"assets/cards/back.png", 
		48, 12, 0
	),
	
	LOCKE_VON_ELVIS(
		CardType.WEALTH, 
		"assets/cards/locke-von-elvis.png", 
		"assets/cards/back.png", 
		52, 13, 0
	),
	
	MUENZSAMMLUNG(
		CardType.WEALTH, 
		"assets/cards/muenzsammlung.png", 
		"assets/cards/back.png", 
		28, 7, 0
	),
	
	OSTALGIESAMMLUNG(
		CardType.WEALTH, 
		"assets/cards/ostalgiesammlung.png", 
		"assets/cards/back.png", 
		8, 2, 0
	),
	
	PLATTENSAMMLUNG(
		CardType.WEALTH, 
		"assets/cards/plattensammlung.png", 
		"assets/cards/back.png", 
		36, 9, 0
	),
	
	SIEGELRING(
		CardType.WEALTH, 
		"assets/cards/siegelring.png", 
		"assets/cards/back.png", 
		24, 6, 0
	),
	
	SPORTWAGEN(
		CardType.WEALTH, 
		"assets/cards/sportwagen.png", 
		"assets/cards/back.png", 
		44, 11, 0
	),
	
	VERGOLDETE_FABRIKEN(
		CardType.WEALTH, 
		"assets/cards/vergoldete-fabriken.png", 
		"assets/cards/back.png", 
		46, 0, 0, 
		
		new CardBehavior() {
			public void update(Player owner) {
				for(Card c : owner.getCards()) {
					if(c.getType().equals(CardType.PRODUCTION)) {
						owner.raiseStatus(5);
					}
				}
			}
		}
	),
	
	WM_BALL(
		CardType.WEALTH, 
		"assets/cards/wm-ball.png", 
		"assets/cards/back.png", 
		32, 8, 0
	),

	// Fabriken
	AZUBI(
		CardType.PRODUCTION, 
		"assets/cards/azubi.png", 
		"assets/cards/back.png", 
		24, 3, 5
	),
	
	BAECKER(
		CardType.PRODUCTION, 
		"assets/cards/baecker.png", 
		"assets/cards/back.png", 
		18, 3, 3
	),
	
	BAUUNTERNEHMER(
		CardType.PRODUCTION, 
		"assets/cards/bauunternehmer.png", 
		"assets/cards/back.png", 
		75, 5, 20
	),
	
	BESTATTER(
		CardType.PRODUCTION, 
		"assets/cards/bestatter.png", 
		"assets/cards/back.png", 
		18, -4, 10
	),
	
	DIE_BAHN(
		CardType.PRODUCTION, 
		"assets/cards/die-bahn.png", 
		"assets/cards/back.png", 
		0, 10, -10
	),
	
	GALERIE(
		CardType.PRODUCTION, 
		"assets/cards/galerie.png", 
		"assets/cards/back.png", 
		39, 0, 0,
		
		new CardBehavior() {
			public void update(Player owner) {
				owner.increaseProduction(owner.getWealthAmount());
			}
		}
	),
	
	KAUFHAUS(
		CardType.PRODUCTION, 
		"assets/cards/kaufhaus.png", 
		"assets/cards/back.png", 
		39, 3, 10
	),
	
	KINO(
		CardType.PRODUCTION, 
		"assets/cards/kino.png", 
		"assets/cards/back.png", 
		48, 6, 10
	),
	
	MOEBELFABRIK(
		CardType.PRODUCTION, 
		"assets/cards/moebelfabrik.png", 
		"assets/cards/back.png", 
		69, 3, 20
	),
	
	MOLKEREI(
		CardType.PRODUCTION, 
		"assets/cards/molkerei.png", 
		"assets/cards/back.png", 
		36, 0, 12
	),
	
	MUSEUM(
		CardType.PRODUCTION, 
		"assets/cards/museum.png", 
		"assets/cards/back.png", 
		21, 3, 4
	),
	
	PLATTENSTUDIO(
		CardType.PRODUCTION, 
		"assets/cards/plattenstudio.png", 
		"assets/cards/back.png", 
		69, 8, 15
	),
	
	SAEGEWERK(
		CardType.PRODUCTION, 
		"assets/cards/saegewerk.png", 
		"assets/cards/back.png", 
		45, 5, 10
	),
	
	SCHLACHTER(
		CardType.PRODUCTION, 
		"assets/cards/schlachter.png", 
		"assets/cards/back.png", 
		18, 1, 5
	),
	
	SUPERMARKT(
		CardType.PRODUCTION, 
		"assets/cards/supermarkt.png", 
		"assets/cards/back.png", 
		33, 1, 10
	),
	
	T_SHIRT_FABRIK(
		CardType.PRODUCTION, 
		"assets/cards/t-shirt-fabrik.png", 
		"assets/cards/back.png", 
		66, 7, 15
	),
	
	TABAKINDUSTRIE(
		CardType.PRODUCTION, 
		"assets/cards/tabakindustrie.png", 
		"assets/cards/back.png", 
		84, 3, 25
	),
	
	THEATER(
		CardType.PRODUCTION, 
		"assets/cards/theater.png", 
		"assets/cards/back.png", 
		36, 6, 6
	),
	
	WERFT(
		CardType.PRODUCTION, 
		"assets/cards/werft.png", 
		"assets/cards/back.png", 
		48, 1, 15
	),
	
	// Spezialkarten
	BAULAND_1(
		CardType.SERVICE, 
		"assets/cards/bauland-1.png", 
		"assets/cards/back.png", 
		20, 0, 0
	),
	
	BAULAND_2(
		CardType.SERVICE, 
		"assets/cards/bauland-2.png", 
		"assets/cards/back.png", 
		35, 3, 0
	),
	
	BAULAND_3(
		CardType.SERVICE, 
		"assets/cards/bauland-3.png", 
		"assets/cards/back.png", 
		55, 8, 0
	),
	
	MEGA_IN(
		CardType.SERVICE, 
		"assets/cards/mega-in.png", 
		"assets/cards/back.png", 
		30, 0, 0
	),
	
	SECURITY(
		CardType.SERVICE, 
		"assets/cards/security.png", 
		"assets/cards/back.png", 
		13, 0, 0
	),
	
	SPONSOR(
		CardType.SERVICE, 
		"assets/cards/sponsor.png", 
		"assets/cards/back.png", 
		52, 2, 0,
		
		new CardBehavior() {
			public void update(Player owner) {
				super.update(owner);
				owner.increaseBasicIncome(10);
			}
		}
	),
	
	SUPERMANAGER(
		CardType.SERVICE, 
		"assets/cards/supermanager.png", 
		"assets/cards/back.png", 
		17, 0, 0
	),
	
	VOLL_IM_TREND(
		CardType.SERVICE, 
		"assets/cards/voll-im-trend.png", 
		"assets/cards/back.png", 
		27, 3, 0
	);
	
	private String _coverFile;
	private String _backFile;
	private CardType _type;
	private int _minimumBid;
	private int _wealthAmount;
	private int _productionAmount;
	private CardBehavior _behavior;
	
	private Card(
			CardType type, 
			String coverFile, 
			String backFile, 
			int minimumBid, 
			int wealthAmount, 
			int productionAmount
	) {
		_coverFile = coverFile;
		_backFile = backFile;
		_type = type;
		_minimumBid = minimumBid;
		_wealthAmount = wealthAmount;
		_productionAmount = productionAmount;
		_behavior = new CardBehavior();
		_behavior.setCard(this);
	}
	
	private Card(
			CardType type, 
			String coverFile, 
			String backFile, 
			int minimumBid, 
			int wealthAmount, 
			int productionAmount, 
			CardBehavior behavior
	) {
		this(type, coverFile, backFile, minimumBid, wealthAmount, productionAmount);
		behavior.setCard(this);
		_behavior = behavior;
	}
	
	public String getCoverFile() {
		return _coverFile;
	}
	
	public String getBackFile() {
		return _backFile;
	}
	
	public CardType getType() {
		return _type;
	}
	
	public int getMinimumBid() {
		return _minimumBid;
	}
	
	public int getWealthAmount() {
		return _wealthAmount;
	}
	
	public int getProductionAmount() {
		return _productionAmount;
	}
	
	public CardBehavior getBehavior() {
		return _behavior;
	}
}