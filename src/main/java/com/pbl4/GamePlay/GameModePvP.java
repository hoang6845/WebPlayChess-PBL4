package com.pbl4.GamePlay;

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

public class GameModePvP {
	public ManagePlayer mgr = new ManagePlayer();
	Stack<Undo> U = new Stack<Undo>();
	public Location Pdie = new Location();
	boolean turn;

	public Stack<Undo> getU() {
		return U;
	}

	public void setU(Stack<Undo> u) {
		U = u;
	}

	int[][] Board = { { 50, 10, 0, 0, 0, 0, -10, -50 }, { 30, 10, 0, 0, 0, 0, -10, -30 },
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

		turn = true;

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

	public int Move(int j, int x1, int y1) {

		if (turn == true) {
			/*
			 * Stack<Location> P = ValidMove(j, Board, Player1.PosB); for (int i = 0; i <
			 * P.getCurrent(); i++) { cout << endl << P.top().x << " " << P.top().y; }
			 */
			Stack<Location> P = mgr.Player[j].ValidMove(Board);
			boolean validMove = false;
			int k = P.size();
			for (int i = 0; i < k; i++) {
				System.out.println(P.lastElement().getX() + " " + P.lastElement().getY());
				if (P.lastElement().getX() == x1 && P.lastElement().getY() == y1) {
					validMove = true;
//					break;
				}
				P.pop();
			}

			if (validMove == false)
				return 0;
			if (Board[x1][y1] > 0) {
				for (int i = 0; i < 16; i++) {
					if (Board[x1][y1] == mgr.Computer[i].getValue() && mgr.Computer[i].getP().getX() == x1
							&& mgr.Computer[i].getP().getY() == y1) {
						mgr.Computer[i].die();
						// undo
						Undo m = new Undo();
						m.setI(i);
						m.setPorC(1);
						m.setBegin(new Location(x1, y1));
						m.setEnd(Pdie);
						U.push(m);

						break;

					}
				}
			}
			Undo n = new Undo();
			// undo

			n.setI(j);
			n.setPorC(-1);
			n.setBegin(new Location(mgr.Player[j].getP().getX(), mgr.Player[j].getP().getY()));
			n.setEnd(new Location(x1, y1));

			Board[x1][y1] = mgr.Player[j].getValue();
			Board[mgr.Player[j].getP().getX()][mgr.Player[j].getP().getY()] = 0;
			mgr.Player[j].setP(x1, y1);

			turn = !turn;
			// kiem tra phong hau
			if (mgr.Player[j].getValue() == -10 && y1 == 0) {
				n.setCheckQueen(true);
				mgr.Player[j].BecomeQueen();
				Board[x1][y1] = mgr.Player[j].getValue();
				U.push(n);
				return j;
			}
			U.push(n);
			banco();
			return -1;
		} else {
			/*
			 * Stack<Location> P = ValidMove(j, Board, Player2.PosA);
			 * 
			 * for (int i = 0; i < P.getCurrent(); i++) { cout << endl << P.top().x << " "
			 * << P.top().y; }
			 */
			Stack<Location> P = mgr.Computer[j].ValidMove(Board);
			boolean validMove = false;
			int k = P.size();
			for (int i = 0; i < k; i++) {
				System.out.println(P.lastElement().getX() + " " + P.lastElement().getY());
				if (P.lastElement().getX() == x1 && P.lastElement().getY() == y1) {
					validMove = true;
					break;
				}
				P.pop();
			}
			if (validMove == false)
				return 0;
			if (Board[x1][y1] < 0) {
				for (int i = 0; i < 16; i++) {
					if (Board[x1][y1] == mgr.Player[i].getValue() && mgr.Player[i].getP().getX() == x1
							&& mgr.Player[i].getP().getY() == y1) {

						mgr.Player[i].die();

						// undo
						Undo m = new Undo();
						m.setI(i);
						m.setPorC(-1);
						m.setBegin(new Location(x1, y1));
						m.setEnd(Pdie);
						U.push(m);

						break;
					}
				}
			}
			Undo n = new Undo();
			// undo

			n.setI(j);
			n.setPorC(1);
			n.setBegin(new Location(mgr.Computer[j].getP().getX(), mgr.Computer[j].getP().getY()));
			n.setEnd(new Location(x1, y1));

			Board[x1][y1] = mgr.Computer[j].getValue();
			Board[mgr.Computer[j].getP().getX()][mgr.Computer[j].getP().getY()] = 0;

			mgr.Computer[j].setP(x1, y1);

			turn = !turn;
			// kiem tra phong hau
			if (mgr.Computer[j].getValue() == 10 && y1 == 7) {
				n.setCheckQueen(true);
				mgr.Computer[j].BecomeQueen();
				Board[x1][y1] = mgr.Computer[j].getValue();
				U.push(n);
				return j;

			}
			U.push(n);
			banco();
			return -1;
		}

	}

	public void undo() {
		boolean y = true;
		Undo R;
		while (y == true && !U.isEmpty()) {
			turn = !turn;
			R = U.lastElement();

			if (R.getPorC() == -1) {
				Board[R.getBegin().getX()][R.getBegin().getY()] = mgr.Player[R.getI()].value;
				Board[R.getEnd().getX()][R.getEnd().getY()] = 0;
				mgr.Player[R.getI()].getP().setX(R.getBegin().getX());
				mgr.Player[R.getI()].getP().setY(R.getBegin().getY());
				mgr.Player[R.getI()].setP(R.getBegin().getX(), R.getBegin().getY());
				if (R.isCheckQueen() == true) {
					mgr.Player[R.getI()].ReverseBecomeQueen();
//		                Player1.Player[R.i]->setValue();
					Board[R.getBegin().getX()][R.getBegin().getY()] = mgr.Player[R.getI()].getValue();
//		                Player1.PosB[R.i].value = Player1.Player[R.i]->getValue()*Player1.Player[R.i]->getIndex();
				}
				U.pop();
				if (U.isEmpty()) {
					y = false;
					break;
				}
				R = U.lastElement();
				if (R.getPorC() == 1 && R.getEnd().getX() == Pdie.getX()) {
					mgr.Computer[R.getI()].alive();

//		                Player2.PosA[R.i].value = Player2.Computer[R.i]->getValue();
					Board[mgr.Computer[R.getI()].getP().getX()][mgr.Computer[R.getI()].getP()
							.getY()] = mgr.Computer[R.getI()].getValue();
					U.pop();
				}
				y = false;

			} else if (R.getPorC() == 1) {
				Board[R.getBegin().getX()][R.getBegin().getY()] = mgr.Computer[R.getI()].getValue();
				Board[R.getEnd().getX()][R.getEnd().getY()] = 0;

				mgr.Computer[R.getI()].setP(R.getBegin().getX(), R.getBegin().getY());
				if (R.isCheckQueen() == true) {

					mgr.Computer[R.getI()].ReverseBecomeQueen();
//		                Player2.Computer[R.i]->setValue();
					Board[R.getBegin().getX()][R.getBegin().getY()] = mgr.Computer[R.getI()].getValue();
//		                Player2.PosA[R.i].value = Player2.Computer[R.i]->getValue();
				}
				U.pop();
				if (U.isEmpty()) {
					y = false;
					break;
				}
				R = U.lastElement();
				if (R.getPorC() == -1 && R.getEnd().getX() == Pdie.getX()) {
					mgr.Player[R.getI()].alive();
//		                Player1.PosB[R.i].value = Player1.Player[R.i]->getValue() * Player1.Player[R.i]->getIndex();
					Board[mgr.Player[R.getI()].getP().getX()][mgr.Player[R.getI()].getP().getY()] = mgr.Player[R.getI()]
							.getValue();
					U.pop();
				}
				y = false;

			}
		}
	}

	int isKingInCheck(int Board[][], Chess[] Computer, Chess[] Player, boolean turn) {

		if (turn) {
			for (int i = 0; i < 16; i++) {
				Stack<Location> P = Player[i].ValidMove(Board);
				int k= P.size();
				for (int j = 0; j < k; j++) {
					if (P.lastElement().getX() == Computer[15].getP().getX()
							&& P.lastElement().getY() == Computer[15].getP().getY())					
						return 1;
					P.pop();
				}
			}
		} else {
			for (int i = 0; i < 16; i++) {
				Stack<Location> P = Computer[i].ValidMove(Board);
				int k= P.size();
				for (int j = 0; j < k; j++) {
					if (P.lastElement().getX() == Player[15].getP().getX()
							&& P.lastElement().getY() == Player[15].getP().getY())
						return -1;
					P.pop();
				}
			}

		}
		return 0;

	}

	// P2 win
	public boolean endGameP2() {
		int arr[][] = new int[8][8];
		for (int i = 0; i < 16; i++) {
			Stack<Location> P = mgr.Player[i].ValidMove(Board);
			int ka= P.size();
			for (int j = 0; j < ka; j++) {
				CopyMang(Board, arr);
				Chess Computer2[] = new Chess[16];
				CopyChess(mgr.Computer, Computer2);
				Chess Player2[] = new Chess[16];
				CopyChess(mgr.Player, Player2);
				int tam = mgr.Player[i].getValue();
				arr[mgr.Player[i].getP().getX()][mgr.Player[i].getP().getY()] = 0;
				if (arr[P.lastElement().getX()][P.lastElement().getY()] > 0) {
					for (int k = 0; k < 16; k++) {
						if ((Computer2[k].getP().getX() == P.lastElement().getX())
								&& (Computer2[k].getP().getY() == P.lastElement().getY())) {
							Computer2[k].die();
							break;
						}
					}
				}

				arr[P.lastElement().getX()][P.lastElement().getY()] = tam;
				Player2[i].setP(P.lastElement().getX(), P.lastElement().getY());
				Player2[i].setValue();

				if (isKingInCheck(arr, Computer2, Player2, !turn) == 0) {
					return false;
				}
				P.pop();
			}
		}
		return true;

	}

	// P1 win
	public boolean endGameP1() {
		int arr[][] = new int[8][8];
		for (int i = 0; i < 16; i++) {
			Stack<Location> P = mgr.Computer[i].ValidMove(Board);
			int ka= P.size();
			for (int j = 0; j < ka; j++) {
				CopyMang(Board, arr);
				Chess Computer2[] = new Chess[16];
				CopyChess(mgr.Computer, Computer2);
				Chess Player2[] = new Chess[16];
				CopyChess(mgr.Player, Player2);
				int tam = mgr.Computer[i].getValue();
				arr[mgr.Computer[i].getP().getX()][mgr.Computer[i].getP().getX()] = 0;
				if (arr[P.lastElement().getX()][P.lastElement().getY()] < 0) {
					for (int k = 0; k < 16; k++) {
						if ((Player2[k].getP().getX() == P.lastElement().getX())
								&& (Player2[k].getP().getY() == P.lastElement().getY())) {
							Player2[i].die();
							break;
						}
					}
				}

				arr[P.lastElement().getX()][P.lastElement().getY()] = tam;
				Computer2[i].setP(P.lastElement().getX(), P.lastElement().getY());
				Computer2[i].setValue();

				if (isKingInCheck(arr, Computer2, Player2, !turn) == 0) {
					/*
					 * bancoao(arr);
					 * System.out.println(P.lastElement().getX()+" "+P.lastElement().getY()+" "+i);
					 */
					return false;
				}
				P.pop();
			}
		}
		return true;

	}

	/*
	 * void bancoao(int Board[][]) { for (int i = 0; i < 8; i++) { for (int j = 0; j
	 * < 8; j++) { if (Board[j][i] == 10) System.out.print(" P"); else if
	 * (Board[j][i] == 50) System.out.print(" R"); else if (Board[j][i] == 30)
	 * System.out.print(" H"); else if (Board[j][i] == 35) System.out.print(" B");
	 * else if (Board[j][i] == 90) System.out.print(" Q"); else if (Board[j][i] ==
	 * 1000) System.out.print(" K"); else if (Board[j][i] == -10)
	 * System.out.print(" p"); else if (Board[j][i] == -50) System.out.print(" r");
	 * else if (Board[j][i] == -30) System.out.print(" h"); else if (Board[j][i] ==
	 * -35) System.out.print(" b"); else if (Board[j][i] == -90)
	 * System.out.print(" q"); else if (Board[j][i] == -1000)
	 * System.out.print(" k"); else System.out.print("  "); } System.out.println();
	 * } }
	 */
	public static void main(String args[]) {
		GameModePvP G = new GameModePvP();
		G.begin();
		G.Move(0, 0, 5);
	}

}
