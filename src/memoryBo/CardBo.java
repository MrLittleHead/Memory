package memoryBo;

public class CardBo {
	
	
	
	private final SymboleBo SYMBOLE_CACHE = SymboleBo.get(0);

	private static int id_Card = 1;
	private boolean visible = false;
	private SymboleBo symbole;

	
	public CardBo() { 
	super();
	}
		
	public CardBo(int motif) 
	{
		super();
		this.symbole = SymboleBo.get(motif);
		
	}

	public boolean isVisible() 
	{
		return this.visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public SymboleBo getSYMBOLE_CACHE() 
	{
		return SYMBOLE_CACHE;
	}

	public SymboleBo getSymbole() 
	{
		return symbole;
	}

	public void setSymbole(SymboleBo symbole) 
	{
		this.symbole = symbole;
	}
	
	public int getId_Card() 
	{
		return id_Card;
	}
	
	public void setId_Card(int id)
	{
	this.id_Card = id; 		
	}

	@Override
	public boolean equals(Object obj) {
		return this.symbole==((CardBo)obj).symbole;
	}

	@Override
	public String toString() 
	{
		if (visible) 
		{
			return "|" + symbole + "|";
		}
		return "|" + SYMBOLE_CACHE +"|";
	}



}


