package memoryBo;

public class CardBo {
	
	
	private int id_Card;
	private final SymboleBo SYMBOLE_CACHE = SymboleBo.get(0);
	private static int counter = 0;
	private boolean visible = false;
	private SymboleBo symbole;


	public CardBo(int motif) 
	{
		super();
		this.symbole = SymboleBo.get(motif);
		this.id_Card = counter;
		++counter;
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
	
	public void setId_Card(int int1)
	{
	this.id_Card = counter; 		
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


