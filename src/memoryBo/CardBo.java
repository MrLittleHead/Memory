package memoryBo;

public class CardBo {


	private final SymboleBo MOTIF_CACHE = SymboleBo.get(0);
	private boolean visible = false;
	private SymboleBo motif;


	public CardBo(int motif) 
	{
		super();
		this.motif = SymboleBo.get(motif);
	}

	public boolean isVisible() 
	{
		return this.visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public SymboleBo getMOTIF_CACHE() 
	{
		return MOTIF_CACHE;
	}

	public SymboleBo getMotif() 
	{
		return motif;
	}

	public void setMotif(SymboleBo motif) 
	{
		this.motif = motif;
	}



	@Override
	public boolean equals(Object obj) {
		return this.motif==((CardBo)obj).motif;
	}

	@Override
	public String toString() 
	{
		if (visible) 
		{
			return "|" + motif + "|";
		}
		return "|" + MOTIF_CACHE +"|";
	}
}


