package engine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;
import model.heroes.Hero;
import model.heroes.Mage;
import model.heroes.Priest;
import view.view;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;

public class Controller implements ActionListener , GameListener{
  
	private view v ;
	private Game g;
	private Hero curr ;
	private Hero Opp ; 
	private selectHero heroSelect ; 
	private ArrayList <JButton> currHand ;
	private ArrayList <JButton> currField ;
	private ArrayList <JButton> OppField;
	private ArrayList <JButton> OppHand ;
	private JButton attacker ;
	private JButton SelectedMinion ; 
	private JButton SelectedHero;
	private boolean power ;
	private boolean spell ;
	private Spell sp ;
	
	public Controller () throws FullHandException, CloneNotSupportedException {
		
		heroSelect = new selectHero () ;
		currHand = new ArrayList<JButton>();
		currField  = new ArrayList<JButton>();
		OppField = new ArrayList<JButton>();
		OppHand = new ArrayList<JButton>();
		while (heroSelect.getP1() == null || heroSelect.getP2() == null){
			System.out.println();
//			System.out.println ("Hero 1 : " + heroSelect.getP1());
//			System.out.println ("Hero 2 : " + heroSelect.getP2());
			continue ;
		}
		System.out.print("DONE SELECTING");
		Hero h1 = heroSelect.getP1() ;
		Hero h2 = heroSelect.getP2();
		//v.setCurrHero(heroSelect.getH1());
		//v.setOppHero(heroSelect.getH2());
		g = new Game (h1,h2);
		g.setListener(this);
		v = new view () ;
		v.getEnd().addActionListener(this);
		curr = g.getCurrentHero();
		Opp = g.getOpponent() ; 
		this.updateCurrHand();
		this.updateOppHand();
		this.updateHeroes();
		v.getCurrHero().addActionListener(this);
		v.getOppHero().addActionListener(this);
	    v.revalidate();
	    v.repaint();
	    power = false ;
	    SelectedMinion = null;
	    
	    
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		if (currHand.contains(b)){
		int x = currHand.indexOf(b)	;
		if (curr.getHand().get(x) instanceof Minion){
			
				
				try {
					curr.playMinion((Minion)curr.getHand().get(x));
				} catch (NotYourTurnException | NotEnoughManaException
						| FullFieldException e1) {
					MyFrame f = new MyFrame (e1.getMessage());
				}
				this.updateCurrField();
				this.updateCurrHand();
				this.updateHeroes();
		}else {
		if (curr.getHand().get(x) instanceof Spell){
			sp = (Spell) curr.getHand().get(x);
			this.CastSpell();
			
		}
		}}
		if (currField.contains(b)){
			if (spell){
				SelectedMinion = b ;
				this.CastSpell();
				SelectedMinion = null ;
				spell = false ;
				
			}else {
			if (power){
				SelectedMinion = b ;
				this.useHeroPower(SelectedMinion) ;
				power = false ;
				SelectedMinion = null ; 
				SelectedHero= null ;
			}
			else {
				if (attacker != null ){
				attacker.setIcon(null);
				}
			int x1 = currField.indexOf(b);
			if (curr.getField().get(x1) instanceof Minion){
				attacker=b;
				MyFrame f =new MyFrame ("Choose your Target i.e Minion or Hero");
				ImageIcon i = new ImageIcon ("Images/attack.jpg");
				Image i1 = i.getImage().getScaledInstance(v.getWidth()/9,v.getHeight()/5,Image.SCALE_DEFAULT);
				i.setImage(i1);
			    b.setIcon(i);
			    v.revalidate();
			    v.repaint();
				
			
			}}
			
		}}
		if (OppField.contains(b)){
			if (spell){
				SelectedMinion = b ;
				this.CastSpell();
				SelectedMinion = null ;
				spell = false ;
			}else {
			if (power){
				SelectedMinion = b ;
				this.useHeroPower(SelectedMinion) ;
				power = false ;
				SelectedMinion = null ; 
				SelectedHero= null ;
			} 
			else if (attacker != null){
			int y = OppField.indexOf(b)	;
			int z = currField.indexOf(attacker);
			try {
				curr.attackWithMinion(curr.getField().get(z),Opp.getField().get(y));
			} catch (CannotAttackException | NotYourTurnException
					| TauntBypassException | InvalidTargetException
					| NotSummonedException e1) {
				MyFrame f = new MyFrame (e1.getMessage());
			
			}}}
			this.updateCurrField();
			this.updateOppField();
			attacker = null ;
			
			
			
		}
      if (b==v.getOppHero()){
    	  if (spell){
				SelectedHero = b ;
				this.CastSpell();
				SelectedHero = null ;
				spell = false ;
      }else {
    	  if (power){
    		  SelectedHero = b ;
    		  this.useHeroPower(SelectedHero);
    		  power = false ;
    			SelectedMinion = null ; 
    			SelectedHero= null ;
    	  }}
    	  if (attacker != null){
			
			int z = currField.indexOf(attacker);
			try {
				curr.attackWithMinion(curr.getField().get(z),Opp);
			} catch (CannotAttackException | NotYourTurnException
					| TauntBypassException | NotSummonedException
					| InvalidTargetException e1) {
				// TODO Auto-generated catch block
				MyFrame f = new MyFrame(e1.getMessage());
			}
					
			attacker = null;
			this.updateCurrField();
			this.updateOppField();
			this.updateHeroes();
      }}
		
		
		if (b.getText() .equals("END TURN")){
			try {
				curr.endTurn();
				curr= g.getCurrentHero();
				Opp = g.getOpponent();
				this.updateCurrField();
				this.updateCurrHand();
				this.updateOppField();
				this.updateOppHand();
				this.updateHeroes();
				
			} catch (FullHandException | CloneNotSupportedException e1) {
				if (e1 instanceof FullHandException){
					String s = e1.getMessage() + "\n" + "CARD BURNED :  ";
					s = s + ((FullHandException) e1).getBurned().toString();
					MyFrame f = new MyFrame (s);
				}else {
					MyFrame f = new MyFrame(e1.getMessage());	

				}
				curr= g.getCurrentHero();
				Opp = g.getOpponent();
				this.updateCurrField();
				this.updateCurrHand();
				this.updateOppField();
				this.updateOppHand();
				this.updateHeroes();}
		}
		if (b==v.getCurrHero()){
			 if (spell){
					SelectedHero = b ;
					this.CastSpell();
					SelectedHero = null ;
					spell = false ;
	      }
	    	  if (power){
	    		  SelectedHero = b ;
	    		  this.useHeroPower(SelectedHero);
	    		  power = false ;
	    			SelectedMinion = null ; 
	    			SelectedHero= null ;
	    	  }
	    	  else {
                this.useHeroPower(SelectedMinion); }
	    	  
				this.updateCurrField();
				this.updateCurrHand();
				this.updateOppField();
				this.updateOppHand();
				this.updateHeroes();
			
			
				
			}}
	
	public void updateCurrField (){
		while (! (currField.isEmpty())){
			currField.remove(0);

		}
		v.getCurrField().removeAll();
		for (int i = 0 ; i <curr.getField().size();i++){
			Card c = curr.getField().get(i);
			MyJButton b = new MyJButton (c.toString(),v.getWidth()/10,v.getHeight()/5);
			
			b.addActionListener(this);
			currField.add(b);
			v.getCurrField().add(b);
		   
		}
		v.revalidate();
		v.repaint();}
	public void updateCurrHand() {
		while (! currHand.isEmpty()){
			currHand.remove(0);
		}
		v.getCurrHand().removeAll();
		
		for (int i = 0 ; i <curr.getHand().size();i++){
			Card c = curr.getHand().get(i);
			MyJButton b = new MyJButton (c.toString2(),v.getWidth()/12,v.getHeight()/5);
			b.addActionListener(this);
			v.getCurrHand().add(b);
			currHand.add(b);
			b.setVisible(true);
			
		}
	v.revalidate();
	v.repaint();}
	public void updateOppField (){
		while (! OppField.isEmpty()){
			OppField.remove(0);
		}
		v.getOppField().removeAll();
		for (int i = 0 ; i <Opp.getField().size();i++){
			Card c = Opp.getField().get(i);
			MyJButton b = new MyJButton (c.toString(),v.getWidth()/10,v.getHeight()/5);
			
			b.addActionListener(this);
			OppField.add(b);
			v.getOppField().add(b);
		    
		}
		v.revalidate();
		v.repaint();}
	public void updateOppHand(){
		while (! OppHand.isEmpty()){
			OppHand.remove(0);
		}
		v.getOppHand().removeAll();
		
		for (int i = 0 ; i <Opp.getHand().size();i++){
			Card c = Opp.getHand().get(i);
			MyJButton b = new MyJButton ("",v.getWidth()/12,v.getHeight()/5);
			b.addActionListener(this);
			
			v.getOppHand().add(b);
			ImageIcon j = new ImageIcon("Images/CardBack.jpg");
			Image i1 = j.getImage().getScaledInstance(v.getWidth()/12,(int)(v.getHeight()/5.2),Image.SCALE_DEFAULT);
			j.setImage(i1);
			b.setIcon(j);
			OppHand.add(b);
			b.setVisible(true); }
	    v.revalidate();
	    v.repaint();
		
	}
	public void updateHeroes (){
		v.getCurrHero().removeAll();
		v.getOppHero().removeAll();
		JTextArea a = new JTextArea ();
		a.setPreferredSize(new Dimension (v.getWidth()/11,v.getHeight()/13));
		
	    a.setEditable(false);
		a.setText(curr.toString());
		a.setVisible(true);
		a.setOpaque(false);
	    float x = v.getWidth()/150;
		a.setFont(a.getFont().deriveFont(x  ));
		v.getCurrHero().add(a,BorderLayout.SOUTH);
		
		JTextArea b = new JTextArea ();
		b.setPreferredSize(new Dimension (v.getWidth()/11,v.getHeight()/13));
	    b.setEditable(false);
		b.setText(Opp.toString());
		b.setVisible(true);
		b.setOpaque(false);
		b.setFont(b.getFont().deriveFont(x));
		v.getOppHero().add(b,BorderLayout.SOUTH);
		v.revalidate();
		v.repaint();
		
	}
	public void useHeroPower (JButton b ){
		int x ;
		if (curr instanceof Mage){
			if (SelectedHero != null || SelectedMinion != null){
			if (SelectedHero != null ){	
			
			if (v.getCurrHero()==b){
				try {
					((Mage)curr).useHeroPower(curr);
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e) {
					MyFrame f = new MyFrame (e.getMessage());

				}
				
			}
			if (v.getOppHero()==b){
				try {
					((Mage)curr).useHeroPower(Opp);
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e) {
					MyFrame f = new MyFrame (e.getMessage());
				}
				
			}
						
			}
			else {
			
			if (currField.contains(b)){
				x = currField.indexOf (b);
				try {
					((Mage)curr).useHeroPower(curr.getField().get(x));
					
					
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e) {
					MyFrame f = new MyFrame (e.getMessage());
								}
			}
			else {
				x = OppField.indexOf (b);
				try {
					((Mage)curr).useHeroPower(Opp.getField().get(x));
					
					
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e) {
					MyFrame f = new MyFrame (e.getMessage());
								}
			}
			
		}}
		else {
			Select s = new Select () ;

			power = true;
		}}
		else {	
	    
		if (curr instanceof Priest){
			if (SelectedHero != null || SelectedMinion != null){
				if (SelectedHero != null ){	
				
				if (v.getCurrHero()==b){
					try {
						((Priest)curr).useHeroPower(curr);
					} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
							| NotYourTurnException | FullHandException
							| FullFieldException | CloneNotSupportedException e) {
						MyFrame f = new MyFrame (e.getMessage());

					}
					
				}
				if (v.getOppHero()==b){
					try {
						((Priest)curr).useHeroPower(Opp);
					} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
							| NotYourTurnException | FullHandException
							| FullFieldException | CloneNotSupportedException e) {
						MyFrame f = new MyFrame (e.getMessage());
					}
					
				}
							
				}
				else {
				
				if (currField.contains(b)){
					x = currField.indexOf (b);
					try {
						((Priest)curr).useHeroPower(curr.getField().get(x));
						
						
					} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
							| NotYourTurnException | FullHandException
							| FullFieldException | CloneNotSupportedException e) {
						MyFrame f = new MyFrame (e.getMessage());
									}
				}
				else {
					x = OppField.indexOf (b);
					try {
						((Priest)curr).useHeroPower(Opp.getField().get(x));
						
						
					} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
							| NotYourTurnException | FullHandException
							| FullFieldException | CloneNotSupportedException e) {
						MyFrame f = new MyFrame (e.getMessage());
									}
				}
				
			}}
			else {
				Select s = new Select () ;

				power = true;
			}}
		else {
			try {
				curr.useHeroPower();
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
					| NotYourTurnException | FullHandException
					| FullFieldException | CloneNotSupportedException e) {
				MyFrame f = new MyFrame (e.getMessage());
			}
		}}
			
		
		this.updateCurrField();
		this.updateCurrHand();
		this.updateOppField();
		this.updateOppHand();
		this.updateHeroes();
		
	    
	    
	}
	public void CastSpell ( ){
		
		
		if ( sp instanceof MinionTargetSpell && sp instanceof HeroTargetSpell ){
			if (SelectedMinion == null && SelectedHero == null){
				Select s = new Select ();
				spell  = true ;
			}else {
				if (SelectedHero != null){
					if (SelectedHero ==v.getCurrHero()){
						try {
							curr.castSpell((HeroTargetSpell)sp, curr);
						} catch (NotYourTurnException | NotEnoughManaException e) {
							MyFrame f = new MyFrame (e.getMessage());
						}}
						else {
							try {
								curr.castSpell((HeroTargetSpell)sp, Opp);
							} catch (NotYourTurnException | NotEnoughManaException e) {
								MyFrame f = new MyFrame (e.getMessage());
							}
						}
						
					}
				else {
					if (SelectedMinion != null)
					{ if (OppField.contains(SelectedMinion)){
						try {
							curr.castSpell((MinionTargetSpell)sp, Opp.getField().get(OppField.indexOf(SelectedMinion)));
						} catch (NotYourTurnException | NotEnoughManaException
								| InvalidTargetException e) {
							MyFrame f = new MyFrame (e.getMessage());
							
						}
					}
					else {
						try {
							curr.castSpell((MinionTargetSpell)sp, curr.getField().get(OppField.indexOf(SelectedMinion)));
						} catch (NotYourTurnException | NotEnoughManaException
								| InvalidTargetException e) {
							MyFrame f = new MyFrame (e.getMessage());
						}

						
					}
					
						
						}
					}
				}
			}
		else {
		if (sp instanceof MinionTargetSpell){
			if (SelectedMinion == null){
				Select1 s = new Select1();
				spell = true ;
			}
			else {
				if (currField.contains(SelectedMinion)){
					try {
						curr.castSpell((MinionTargetSpell) sp, curr.getField().get(currField.indexOf(SelectedMinion)));
					} catch (NotYourTurnException | NotEnoughManaException
							| InvalidTargetException e) {
						MyFrame f = new MyFrame (e.getMessage());
					}
				}
				if (OppField.contains(SelectedMinion)){
					try {
						curr.castSpell((MinionTargetSpell) sp, Opp.getField().get(OppField.indexOf(SelectedMinion)));
					} catch (NotYourTurnException | NotEnoughManaException
							| InvalidTargetException e) {
						MyFrame f = new MyFrame (e.getMessage());
					}
					
				}
			}
		}else {
		if ( sp instanceof HeroTargetSpell){
				
		}}}
        if (sp instanceof LeechingSpell){
        	if (SelectedMinion == null){
				Select1 s = new Select1();
				spell = true ;
			}
			else {
				if (currField.contains(SelectedMinion)){
					try {
						curr.castSpell((LeechingSpell) sp, curr.getField().get(currField.indexOf(SelectedMinion)));
					} catch (NotYourTurnException | NotEnoughManaException
							e) {
						MyFrame f = new MyFrame (e.getMessage());
					}
				}
				if (OppField.contains(SelectedMinion)){
					try {
						curr.castSpell((LeechingSpell) sp, Opp.getField().get(OppField.indexOf(SelectedMinion)));
					} catch (NotYourTurnException | NotEnoughManaException
							 e) {
						MyFrame f = new MyFrame (e.getMessage());
					}
					
				}
			}
			
		}
        if (sp instanceof FieldSpell){
        	try {
				curr.castSpell((FieldSpell)sp);
			} catch (NotYourTurnException | NotEnoughManaException e) {
				MyFrame f = new MyFrame (e.getMessage());
			}
			
		}
        if (sp instanceof AOESpell){
        	try {
				curr.castSpell((AOESpell)sp, Opp.getField());
			} catch (NotYourTurnException | NotEnoughManaException e) {
				MyFrame f = new MyFrame (e.getMessage());
			}
			
		}

		this.updateCurrField();
		this.updateCurrHand();
		this.updateOppField();
		this.updateOppHand();
		this.updateHeroes();
        
		
		
	}

		
	
		
	
	public static void main(String[] args) throws FullHandException, CloneNotSupportedException {

	    Controller n = new Controller();
			
		}

	@Override
	public void onGameOver() {	
		v.dispose();
		MyFrame2 f = new MyFrame2("THE WINNER IS :" + curr.getName() );
	}
	

}
