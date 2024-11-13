package com.pbl4.GamePlay;

import java.util.Scanner;
import java.util.Stack;

import com.pbl4.model.bean.Bishop;
import com.pbl4.model.bean.Castle;
import com.pbl4.model.bean.Chess;
import com.pbl4.model.bean.King;
import com.pbl4.model.bean.Knight;
import com.pbl4.model.bean.Location;
import com.pbl4.model.bean.ManagePlayer;
import com.pbl4.model.bean.Pawn;
import com.pbl4.model.bean.Queen;
import com.pbl4.model.bean.Undo;

public class GameModeAI {
	public ManagePlayer mgr = new ManagePlayer();
	public Stack<Undo> U = new Stack<Undo>();
	Location Pdie = new Location();
	public int[][] Board = { { 50, 10, 0, 0, 0, 0, -10, -50 }, { 30, 10, 0, 0, 0, 0, -10, -30 },
			{ 35, 10, 0, 0, 0, 0, -10, -35 }, { 90, 10, 0, 0, 0, 0, -10, -90 }, { 1000, 10, 0, 0, 0, 0, -10, -1000 },
			{ 35, 10, 0, 0, 0, 0, -10, -35 }, { 30, 10, 0, 0, 0, 0, -10, -30 }, { 50, 10, 0, 0, 0, 0, -10, -50 } };

	public void begin() {
		int[][] board = { { 50, 10, 0, 0, 0, 0, -10, -50 }, { 30, 10, 0, 0, 0, 0, -10, -30 },
				{ 35, 10, 0, 0, 0, 0, -10, -35 }, { 90, 10, 0, 0, 0, 0, -10, -90 },
				{ 1000, 10, 0, 0, 0, 0, -10, -1000 }, { 35, 10, 0, 0, 0, 0, -10, -35 },
				{ 30, 10, 0, 0, 0, 0, -10, -30 }, { 50, 10, 0, 0, 0, 0, -10, -50 } };
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Board[i][j] = board[i][j];
			}
		}
		mgr.begin();

		Pdie.setX(-100); 
		Pdie.setY(-100); 

	}

	public void banco() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (Board[j][i] == 10)
					System.out.print(" P");
				else if (Board[j][i] == 50)
					System.out.print(" R");
				else if (Board[j][i] == 30)
					System.out.print(" H");
				else if (Board[j][i] == 35)
					System.out.print(" B");
				else if (Board[j][i] == 90)
					System.out.print(" Q");
				else if (Board[j][i] == 1000)
					System.out.print(" K");
				else if (Board[j][i] == -10)
					System.out.print(" p");
				else if (Board[j][i] == -50)
					System.out.print(" r");
				else if (Board[j][i] == -30)
					System.out.print(" h");
				else if (Board[j][i] == -35)
					System.out.print(" b");
				else if (Board[j][i] == -90)
					System.out.print(" q");
				else if (Board[j][i] == -1000)
					System.out.print(" k");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
	}

	public int valueCal(int arr[][]) {
		int sum = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				sum += arr[i][j];
			}
		}

		return sum;
	}

	public void CopyMang(int begin[][], int end[][]) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				end[i][j] = begin[i][j];
			}
		}
	}


	public void CopyChess(Chess begin[], Chess end[]) {
	    for (int i = 0; i < 16; i++) {
	        if (begin[i] instanceof Pawn) {
	            end[i] = new Pawn((Pawn) begin[i]);
	        } else if (begin[i] instanceof Castle) {
	            end[i] = new Castle((Castle) begin[i]);
	        } else if (begin[i] instanceof Bishop) {
	            end[i] = new Bishop((Bishop) begin[i]);
	        } else if (begin[i] instanceof Knight) {
	            end[i] = new Knight((Knight) begin[i]);
	        } else if (begin[i] instanceof Queen) {
	            end[i] = new Queen((Queen) begin[i]);
	        } else if (begin[i] instanceof King) {
	            end[i] = new King((King) begin[i]);
	        } else {
	            end[i] = new Chess(begin[i]);
	        }
	    }
	}


	public int moveOder(int arr[][], int alpha, int beta, int depth, int selectDepth, boolean turn,  Chess Computer[],
			Chess Player[]) {
		if ((depth == 0) || (Computer[15].value == -1) || (Player[15].value == -1)) {
			return valueCal(arr);
		}
		if (turn == true) {
			for (int i = 0; i < 16; i++) {
				Stack<Location> Parr = Computer[i].ValidMove(arr);
				int ParrL = Parr.size();
				for (int j = 0; j < ParrL; j++) {
					int b[][] = new int[8][8];
					CopyMang(arr, b);
					Chess Computer2[] = new Chess[16];
					CopyChess(Computer, Computer2);
					Chess Player2[] = new Chess[16];
					CopyChess(Player, Player2);
					int tam = b[Computer[i].getP().getX()][Computer[i].getP().getY()];
					b[Computer[i].getP().getX()][Computer[i].getP().getY()] = 0;
					// an quan
					if (b[Parr.lastElement().getX()][Parr.lastElement().getY()] < 0) {
						for (int k = 0; k < 16; k++) {
							if ((Player2[k].getP().getX() == Parr.lastElement().getX())
									&& (Player2[k].getP().getY() == Parr.lastElement().getY())) {
								Player2[k].die();
								break;
							}
						}
					}
					// phong hau
					if ((Parr.lastElement().getY() == 7) && (i >= 0) && (i < 8)) {
						tam = 90;
					}
					// thay doi gia tri tren ban co
					b[Parr.lastElement().getX()][Parr.lastElement().getY()] = tam;

					// thay doi chi so quan hien tai trong mang Computer2(mang nhap cua Computer)
					Computer2[i].setP(Parr.lastElement().getX(), Parr.lastElement().getY());
					Computer2[i].value = tam;

					int tmp = moveOder(b, alpha, beta, depth - 1, selectDepth, false, Computer2, Player2);
					if (alpha < tmp) {
						alpha = tmp;
						if (depth == selectDepth) {

							mgr.MoveOderCurent = i;
							mgr.MoveOderNext = Parr.lastElement();

						}
						if (alpha >= beta)
							return alpha;
					}
					Parr.pop();
				}
			}
			return alpha;
		} else {
			for (int i = 0; i < 16; i++) {
				Stack<Location> Parr = Player[i].ValidMove(arr);
				int ParrL = Parr.size();

				for (int j = 0; j < ParrL; j++) {
					int b[][] = new int[8][8];
					CopyMang(arr, b);
					Chess Computer1[] = new Chess[16];
					CopyChess(Computer, Computer1);
					Chess Player1[] = new Chess[16];
					CopyChess(Player, Player1);
					int tam = b[Player[i].getP().getX()][Player[i].getP().getY()]; // tam=Player[i].getValue;
					b[Player[i].getP().getX()][Player[i].getP().getY()] = 0;
					// an quan
					if (b[Parr.lastElement().getX()][Parr.lastElement().getY()] > 0) {
						for (int k = 0; k < 16; k++) {
							if ((Computer1[k].getP().getX() == Parr.lastElement().getX())
									&& (Computer1[k].getP().getY() == Parr.lastElement().getY())) {
								Computer1[k].die();
								break;
							}
						}
					}
					// phong hau
					if ((Parr.lastElement().getY() == 0) && (i >= 0) && (i < 8)) {
						tam = -90;
					}
					// thay doi gia tri tren ban co
					b[Parr.lastElement().getX()][Parr.lastElement().getY()] = tam;
					Player1[i].setP(Parr.lastElement().getX(), Parr.lastElement().getY());
					Player1[i].value = tam*Player1[i].getIndex();

					int tmp = moveOder(b, alpha, beta, depth - 1, selectDepth, true, Computer1, Player1);

					if (beta > tmp) {
						beta = tmp;
					}
					if (alpha >= beta)
						return beta;
					Parr.pop();
				}
			}
			return beta;
		}
	}
	
    public void AiEat()
    {
        for (int i = 0; i < 16; i++)
        {
            if ((mgr.Player[i].getP().getX() == mgr.MoveOderNext.getX()) && (mgr.Player[i].getP().getY() == mgr.MoveOderNext.getY()) && (Board[mgr.MoveOderNext.getX()][mgr.MoveOderNext.getY()] == mgr.Player[i].getValue()))
            {
                mgr.Player[i].die();

                //undo       
                Undo m = new Undo();
                m.setI(i);
                m.setPorC(-1); //quan nguoi nen la -1
                m.setBegin(new Location(mgr.MoveOderNext.getX(),mgr.MoveOderNext.getY()));
                m.setEnd(Pdie);
                U.push(m);
            }
        }
    }
    
    public int MoveB(int j, int x1, int y1)
    {
//        Stack<Location> P = mgr.Player[j].ValidMove(Board);
//        for (int i = 0; i < P.size(); i++)
//        {
//            System.out.println(P.lastElement().x+" "+ P.lastElement().y);
//        }
        if (Board[x1][y1] > 0)
        {
            for (int i = 0; i < 16; i++)
            {
                if (Board[x1][y1] == mgr.Computer[i].getValue() && mgr.Computer[i].getP().getX() == x1 && mgr.Computer[i].getP().getY() == y1)
                {
                    mgr.Computer[i].die();
                    //undo
                    Undo m = new Undo();
                    m.setI(i);
                    m.setPorC(1); //quan may nen la 1
                    m.setBegin(new Location(x1,y1));
                    m.setEnd(Pdie);
                    U.push(m);

                    break;
                }
            }
        }
        Undo n = new Undo();
        // kiem tra phong hau
        //undo

        
        n.setI(j);
        n.setPorC(-1);
        n.setBegin(new Location(mgr.Player[j].getP().getX(),mgr.Player[j].getP().getY()));
    
        n.setEnd(new Location(x1,y1));
        

        Board[x1][y1] = mgr.Player[j].getValue();
        Board[mgr.Player[j].getP().getX()][mgr.Player[j].getP().getY()] = 0;
        mgr.Player[j].setP(x1, y1);
        if (mgr.Player[j].getValue()==-10 && y1==0){
            n.setCheckQueen(true);
            mgr.Player[j].BecomeQueen();
            Board[x1][y1] = mgr.Player[j].getValue();
            U.push(n);
            return j;
        }
        U.push(n);
        return -1;
    }
    
    public int MoveA()
    {
    	System.out.println("\nMáy đi đến:"+ mgr.MoveOderCurent+" "+mgr.MoveOderNext.getX()+","+mgr.MoveOderNext.getY());
    	System.out.println("\nvalue:"+mgr.Computer[mgr.MoveOderCurent].getValue());
        if (Board[mgr.MoveOderNext.getX()][mgr.MoveOderNext.getY()] < 0)
            AiEat();
        Undo n = new Undo();
        //undo
        
        n.setI(mgr.MoveOderCurent);
        n.setPorC(1);
        n.setBegin(new Location(mgr.Computer[mgr.MoveOderCurent].getP().getX(),mgr.Computer[mgr.MoveOderCurent].getP().getY())) ;
        n.setEnd(new Location(mgr.MoveOderNext.getX(),mgr.MoveOderNext.getY()));
        

        Board[mgr.MoveOderNext.getX()][mgr.MoveOderNext.getY()] = mgr.Computer[mgr.MoveOderCurent].getValue();
        Board[mgr.Computer[mgr.MoveOderCurent].getP().getX()][mgr.Computer[mgr.MoveOderCurent].getP().getY()] = 0;
        mgr.Computer[mgr.MoveOderCurent].setP(mgr.MoveOderNext.getX(), mgr.MoveOderNext.getY());
        //kiem tra phong hau
        if (mgr.Computer[mgr.MoveOderCurent].getValue()==10 && mgr.MoveOderNext.getY()==7){
            n.setCheckQueen(true);
            mgr.Computer[mgr.MoveOderCurent].BecomeQueen();
            Board[mgr.MoveOderNext.getX()][mgr.MoveOderNext.getY()] = mgr.Computer[mgr.MoveOderCurent].getValue();
            U.push(n);
            return mgr.MoveOderCurent;
        }
        U.push(n);
        return -1;

    }
    
    public void undo() {
    	boolean y = true;
        Undo R;

        while (y == true && !U.isEmpty()) {
            R = U.lastElement();

            if (R.getPorC() == 1) {
                Board[R.getBegin().getX()][R.getBegin().getY()] = mgr.Computer[R.getI()].value;
                Board[R.getEnd().getX()][R.getEnd().getY()] = 0;
//                chessChoose = R.i;
                mgr.Computer[R.getI()].setP(R.getBegin().getX(), R.getBegin().getY());
                if (R.isCheckQueen() == true) {
                    mgr.Computer[R.getI()].ReverseBecomeQueen(); 
                    mgr.Computer[R.getI()].setValue();
                    Board[R.getBegin().getX()][R.getBegin().getY()] = mgr.Computer[R.getI()].getValue();
                }
                U.pop();
                y = true;

            }
            else if (R.getPorC() == -1)
            {
                if (R.getEnd().getX() == Pdie.getX()) {
                    mgr.Player[R.getI()].alive();
                    y = true;

                }
                else {
                    y = false;
                }
                Board[R.getBegin().getX()][R.getBegin().getY()] = mgr.Player[R.getI()].getValue();
                if (R.getEnd().getX() != Pdie.getX()) Board[R.getEnd().getX()][R.getEnd().getY()] = 0;
//                chessChoose = R.i + 16;
                mgr.Player[R.getI()].setP(R.getBegin().getX(), R.getBegin().getY());
                if (R.isCheckQueen() == true) {
                    mgr.Player[R.getI()].ReverseBecomeQueen();
                    mgr.Player[R.getI()].setValue();
                    Board[R.getBegin().getX()][R.getBegin().getY()] = mgr.Player[R.getI()].getValue();
                }
                U.pop();
                if (y == false && !U.isEmpty())
                {

                    R = U.lastElement();

                    if (R.getEnd().getX() == Pdie.getX()) {
                        mgr.Computer[R.getI()].alive();
                        Board[mgr.Computer[R.getI()].getP().getX()][mgr.Computer[R.getI()].getP().getY()] = mgr.Computer[R.getI()].getValue();
                        U.pop();

                    }
                }
            }
        }

    }

	public void canGo(Chess N) {
		Stack<Location> P = N.ValidMove(Board);
		for (int i = 0; i < P.size(); i++) {
			System.out.print(P.get(i).getX() + ", " + P.get(i).getY() + "\n");
		}
	}

	public static void main(String[] arg) {
//		GameModeAI Game = new GameModeAI();
//		Game.begin();
//		Game.banco();
//		Stack<Location> P = Game.mgr.Player[5].ValidMove(Game.Board);
//		for (int i = 0; i < P.size(); i++) {
//			System.out.print(P.get(i).x + ", " + P.get(i).y + "\n");
//		}
//		Game.canGo(Game.mgr.Computer[0]);
		Chess test[] = new Chess[16];
//
//		Game.CopyChess(Game.mgr.Computer, test);
//		
//		System.out.println(Game.mgr.Computer[0].getValue());
		
		GameModeAI Test = new GameModeAI();
		Test.begin();

		
		
		while (true) {
			Test.banco();
			Scanner input = new Scanner(System.in);
			int j;
			System.out.println("Đi quân nào: ");
			j = input.nextInt();
			Test.canGo(Test.mgr.Player[j]);
			System.out.println("Vị trí muốn đến:  ");
			int m,n;
			m= input.nextInt();
			n= input.nextInt();
			Test.MoveB(j, m, n);
			System.out.println("Đã đến ");
			Test.moveOder(Test.Board, -10000, 10000, 2, 2, true, Test.mgr.Computer, Test.mgr.Player);
			Test.CopyChess(Test.mgr.Computer, test);
			for (int i = 0; i < 16; i++) {
				System.out.print("quân" + i + ": " + test[i].getValue() + " " + test[i].getP().getX() + " " + test[i].getP().getY());
				System.out.println();
				Stack<Location> Pi = test[i].ValidMove(Test.Board);
				if (Pi.size()==0) System.out.print("none");
				for (int k = 0; k < Pi.size(); k++) {
					System.out.print(Pi.get(k).getX() + ", " + Pi.get(k).getY() + " ; ");
				}
				System.out.println();
			}
			Test.banco();
		
			Test.MoveA();
			System.out.println("UNDO?");
			if (input.nextInt()==1) {
				Test.undo();
			}
		}
	}
}
