package memoryControleur;

import memoryBo.CardBo;
import memoryBo.SymboleBo;
import memoryDAO.CardDAO;

public class Main {
	
	public static void main(String[] args) {
		
		
			CardBo c1 = new CardBo(5);
			CardBo readCard;
			
			
			CardDAO testCard = new CardDAO();
			testCard.create(c1);
			c1.setSymbole(SymboleBo.get(7));
			testCard.update(c1);
			readCard = testCard.read(c1.getId_Card());
			readCard.setVisible(true);
			System.out.println(readCard);
			//testCard.delete(c1);
			//new MemoryProgram();
	
			
	}
}
