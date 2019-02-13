package memoryBo;

public enum SymboleBo {
	CACHE,TIGRE,CHAT,CROCO,CHIEN,BOA,POULE,TREX,PANDA,LAPIN,VACHE;

	private static final SymboleBo[] TABLEAU = SymboleBo.values();
	public static final int NBR_SYMBOLES = TABLEAU.length;
	public static SymboleBo get(int i) 
	{
		return TABLEAU[i];
	}

	@Override
	public String toString() 
	{
		String texte = "";
		switch (this) 
		{
		case CACHE:			texte = " *** ";		break;
		case TIGRE:			texte = "tigre";		break;
		case CHAT:			texte = "chat ";		break;
		case CROCO:			texte = "croco";		break;
		case CHIEN:			texte = "chien";		break;
		case BOA:			texte =	" boa ";		break;
		case POULE:			texte = "poule";		break;
		case TREX:			texte = "TREX ";		break;
		case PANDA:			texte = "panda";		break;
		case LAPIN:			texte = "lapin";		break;
		case VACHE:			texte = "vache";		break;
		default: 			texte = "null ";		break;
		}
		return texte;
	}
}
