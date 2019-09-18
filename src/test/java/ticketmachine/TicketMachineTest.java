package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
        // S3 : on n'imprime pas le ticket si le montant inséré est insuffisant
        public void pasAssezDargent(){
                assertEquals("il a imprimé le ticket avec un montant insuffisant d'argent",false,machine.printTicket());
        }
        
        @Test
        // S4 : on imprime le ticket si le montant inséré est suffisant
        public void printTicketIfMoneyIsEnnough(){
                machine.insertMoney(PRICE);
                assertEquals("il n'imprime pas le ticket alors qu'il y a assez d'argent",true,machine.printTicket());
        }
        
        @Test
        // S5 : quand on imprime le ticket la balance est décrémenté du prix du ticket
        public void decrementeIfTicketPrint(){
            machine.insertMoney(PRICE);
            machine.printTicket();
            assertEquals("la balance n'est pas décrémenté lors de l'impression du ticket",PRICE-PRICE,machine.getBalance());
        }
        
        @Test
        // S6 : le montant collecté est mis a jour quand on imprime un ticket
        public void majDuMontantCollecte(){
            assertEquals("le montant collecté ne se met pas a jour",0,machine.getTotal());
            machine.insertMoney(PRICE);
            machine.printTicket();
            assertEquals("le montant collecté ne se met pas a jour",PRICE,machine.getTotal());
        }
        
        @Test
        // S7 : la machine rend l'argent quand on appuit sur refund
        public void refundLargent(){
            machine.insertMoney(80);
            assertEquals("la machine ne rend pas l'argent lorsque l'on appuit sur refund",80,machine.refund());            
        }
        
        @Test 
        // S8 : remet la balance a 0 quand on appuis sur refund
        public void refund(){
            machine.insertMoney(60);
            machine.refund();
            assertEquals("la machine ne remet pas la balance a 0",machine.getBalance(),0);
        }
        

        
        @Test (expected = IllegalArgumentException.class)
        // S9 : on ne peut pas insérer un montant negatif 
        public void argentCollecte(){
            machine.insertMoney(-50);
        }
        
        @Test (expected = IllegalArgumentException.class)
        // S10 : on ne peut pas créer de machine dont le prix d'un ticket est negatif
        public void machinePrixNegatif() {
                new TicketMachine(-4);
        } 
}
