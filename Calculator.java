import java.awt.*;
import java.awt.event.*;
import javax.swing.* ;
import javax.swing.JOptionPane ;
import java.io.*;

public class Calculator extends JFrame
                implements ActionListener {
//jar cf jar-file input-file(s);


   private JButton bOne, bTwo, bThree, bFour, bFive,
                   bSix, bSeven, bEight, bNine,  bZero, bExit,
                   bMult, bDiv, bSub, bPlus, bCEnt, bCAll, bEquals,
                   bPlusMinus, bExp, bMPlus, bMMinus,
                   bFact, bOneDivX, bSqrt, bDecPt ;
   private JTextField enterBox ;
   private JPanel textPanel, exitPanel, buttonPanel, functionPanel ;
   private Container c ;
   private Font         font;
   private String firstNum = new String(""), secondNum  = new String(""), tempStr ;
   private boolean myDiag = false, result = true ,
                   firstNumSet = false, secondNumSet = false,
                   pendingOp  = false ;
   private double aNum, dNum1 = 0.0, dNum2 = 0.0 , answer = 0.0,
                   tempD = 0.0 , minusOne = -1.0 ;
   private double dArray[] = new double[ 10 ]  ;
   private int opCode = -1, tempInt = 0, tempInt2 = 0,
                 dArrayPtr = 0, pendingOpCode = -1 ;

   
   Calculator() {

      super( "Calculator" );
      setup() ;

      setSize( 350, 300 );      
      setLocation( 200, 200 ) ; 
      show();                  
   }

   
   private void setup()   {
      c = getContentPane() ;


      
      enterBox  = new JTextField( 15 ) ;
      enterBox.setText( "..........." );
      enterBox.setEditable( false );
      enterBox.setBackground( Color.white );
      enterBox.setHorizontalAlignment( JTextField.RIGHT );
      enterBox.addKeyListener(
     
         new KeyAdapter() {
            public void keyPressed ( KeyEvent e )   {
               if ( result ) {
                  MyPrint( "The value of result is " + result +
                   "this is from the enterbox keylistener." );
                  result = false ;
                  enterBox.setText( "" );
               }
            }
         }
      );
      textPanel = new JPanel() ;
      textPanel.add(enterBox ) ;

      c.add( textPanel , BorderLayout.NORTH ) ;

     
      buttonPanel = new JPanel() ;
      c.add( buttonPanel , BorderLayout.CENTER ) ;

      bZero   = new JButton( "0" ) ;
      bZero.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      bOne    = new JButton( "1" ) ;
      bTwo    = new JButton( "2" ) ;
      bThree  = new JButton( "3" ) ;
      bFour   = new JButton( "4" ) ;
      bFive   = new JButton( "5" ) ;
      bSix    = new JButton( "6" ) ;
      bSeven  = new JButton( "7" ) ;
      bEight  = new JButton( "8" ) ;
      bNine   = new JButton( "9" ) ;
      bExit   = new JButton( "Exit" ) ;
      bMult   = new JButton( "*" ) ;
      bMult.setFont( new Font("Sanserif", Font.BOLD, 20 ) );
      bDiv    = new JButton( "/" ) ;
      bDiv.setFont( new Font("Sanserif", Font.BOLD, 20 ) );
      bSub    = new JButton( "-" ) ;
      bSub.setFont( new Font("Sanserif", Font.BOLD, 24 ) );
      bPlus   = new JButton( "+" ) ;
      bPlusMinus = new JButton( "+/-" ) ;
      bPlusMinus.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      bEquals = new JButton( "=" ) ;
      bEquals.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      bPlus = new JButton( "+" ) ;
      bPlus.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      bExp = new JButton( "Pow" ) ;
      bExp.setFont( new Font("Sanserif", Font.BOLD, 12 ) );
      bOneDivX = new JButton( "1/x" ) ;
      bOneDivX.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      bSqrt = new JButton( "SQRT" ) ;
      bSqrt.setFont( new Font("Sanserif", Font.BOLD, 12 ) );
      bDecPt = new JButton( "." ) ;
      bDecPt.setFont( new Font("Sanserif", Font.BOLD, 22 ) );

      
      bZero.addActionListener( this );
      bOne.addActionListener( this );
      bTwo.addActionListener( this );
      bThree.addActionListener( this );
      bFour.addActionListener( this );
      bFive.addActionListener( this );
      bSix.addActionListener( this );
      bSeven.addActionListener( this );
      bEight.addActionListener( this );
      bNine.addActionListener( this );
      bExit.addActionListener( this );
      bMult.addActionListener( this );
      bDiv.addActionListener( this );
      bSub.addActionListener( this );
      bPlus.addActionListener( this );
      bPlusMinus.addActionListener( this );
      bEquals.addActionListener( this );
      bExp.addActionListener( this );
      bOneDivX.addActionListener( this );
      bSqrt.addActionListener( this );
      bDecPt.addActionListener( this );

     
      buttonPanel.add( bSeven ) ;
      buttonPanel.add( bEight ) ;
      buttonPanel.add( bNine ) ;
      buttonPanel.add( bMult ) ;

      buttonPanel.add( bFour ) ;
      buttonPanel.add( bFive ) ;
      buttonPanel.add( bSix ) ;
      buttonPanel.add( bDiv ) ;

      buttonPanel.add( bOne ) ;
      buttonPanel.add( bTwo ) ;
      buttonPanel.add( bThree ) ;
      buttonPanel.add( bSub ) ;

      buttonPanel.add( bPlusMinus ) ;
      buttonPanel.add( bZero ) ;
      buttonPanel.add( bDecPt ) ;
      buttonPanel.add( bPlus ) ;

      buttonPanel.add( bExp );
      buttonPanel.add( bOneDivX );
      buttonPanel.add( bSqrt );
      buttonPanel.add( bEquals ) ;

      buttonPanel.setLayout( new GridLayout( 6, 5, 5, 5  ) );

     
      functionPanel = new JPanel() ;
      c.add( functionPanel , BorderLayout.EAST ) ;
      functionPanel.setLayout( new GridLayout( 6, 1, 5, 3  ) );

      bCEnt   = new JButton( "CE" ) ;
      bCEnt.setFont( new Font("Sanserif", Font.BOLD, 14 ) );
      bCAll   = new JButton( "CA" ) ;
      bCAll.setFont( new Font("Sanserif", Font.BOLD, 14 ) );
      bMPlus  = new JButton( "M+" ) ;
      bMPlus.setFont( new Font("Sanserif", Font.BOLD, 14 ) );
      bMMinus = new JButton( "M-" ) ;
      bMMinus.setFont( new Font("Sanserif", Font.BOLD, 14 ) );
      bFact = new JButton( "! n" ) ;
      bFact.setFont( new Font("Sanserif", Font.BOLD, 20 ) );


      
      functionPanel.add( bCEnt ) ;
      functionPanel.add( bCAll );
      functionPanel.add( bMPlus );
      functionPanel.add( bMMinus );
      functionPanel.add( bFact );


      bCEnt.addActionListener( this );
      bCEnt.setBackground( Color.blue ) ;
      bCEnt.setForeground( Color.white );
      bCAll.addActionListener( this );
      bCAll.setBackground( Color.blue ) ;
      bCAll.setForeground( Color.white );
      bMPlus.addActionListener( this );
      bMPlus.setBackground( Color.blue ) ;
      bMPlus.setForeground( Color.white );
      bMMinus.addActionListener( this );
      bMMinus.setBackground( Color.blue ) ;
      bMMinus.setForeground( Color.white );
      bFact.addActionListener( this );
      bFact.setBackground( Color.blue ) ;
      bFact.setForeground( Color.white );


      
      exitPanel = new JPanel() ;
      c.add( exitPanel , BorderLayout.SOUTH ) ;
      bExit   = new JButton( "Exit" ) ;
      bExit.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      bExit.setBackground( Color.red ) ;
      bExit.setForeground( Color.white );
//      bAbout = new JButton( "About" ) ;
//      bAbout.setFont( new Font("Sanserif", Font.BOLD, 14 ) );
//      bAbout.setBackground( Color.darkGray ) ;
//      bAbout.setForeground( Color.white );

      
      exitPanel.add( bExit ) ;

//      exitPanel.add( bAbout ) ;

      bExit.addActionListener( this );

//      bAbout.addActionListener( this );

   }

   
   public void actionPerformed( ActionEvent e )    {
      if ( result ) {
        
         MyPrint( "The value of result is " + result );
         result = false ;
         enterBox.setText( "" );
      }

      if ( !pendingOp && opCode == 99 ) {
        
         enterBox.setText( "" );
         MyPrint( "The value of opCode is " + opCode );
         pendingOpCode = -1 ;
         firstNumSet = false ;
         firstNum = "" ;
      }

      if ( e.getSource() == bDecPt ) {
         MyPrint( "The decimal point button was pressed." );
         enterBox.setText( enterBox.getText() + "." ) ;
      }
      else if ( e.getSource() == bZero ) {
         MyPrint( "The zero button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "0" ) ;
      }
      else  if ( e.getSource() == bOne ) {
         MyPrint( "The one button was pressed. And pendingOp is " + pendingOp  );
         enterBox.setText( enterBox.getText() + "1" ) ;
      }
      else  if ( e.getSource() == bTwo ) {
         MyPrint( "The two button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "2" ) ;
      }
      else  if ( e.getSource() == bThree ) {
         MyPrint( "The Three button was pressed. And pendingOp is " + pendingOp);
         enterBox.setText( enterBox.getText() + "3" ) ;
      }
      else  if ( e.getSource() == bFour ) {
         MyPrint( "The Four button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "4" ) ;
      }
      else  if ( e.getSource() == bFive ) {
         MyPrint( "The Five button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "5" ) ;
      }
      else  if ( e.getSource() == bSix ) {
         MyPrint( "The Six button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "6" ) ;
      }
      else  if ( e.getSource() == bSeven ) {
         MyPrint( "The Seven button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "7" ) ;
      }
      else  if ( e.getSource() == bEight ) {
         MyPrint( "The Eight button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "8" ) ;
      }
      else  if ( e.getSource() == bNine ) {
         MyPrint( "The Nine button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "9" ) ;
      }
      else  if ( e.getSource() == bExit ) {
         dispose();
      }
      else  if ( e.getSource() == bMult ) {
         MyPrint( "The Mult button was pressed. And pendingOp is " + pendingOp  );
         multOp() ;
      }
      else  if ( e.getSource() == bDiv ) {
         MyPrint( "The Div button was pressed. And pendingOp is " + pendingOp  );
         divOp() ;
      }
      else  if ( e.getSource() == bSub ) {
         MyPrint( "The Sub button was pressed. And pendingOp is " + pendingOp  );
         subOp() ;
      }
      else  if ( e.getSource() == bPlus ) {
         MyPrint( "The Plus button was pressed. And pendingOp is " + pendingOp  );
         plusOp()    ;
      }
      else  if ( e.getSource() == bCEnt ) {
         MyPrint( "The CEnt button was pressed." );
         enterBox.setText( "" )  ;
      }
      else  if ( e.getSource() == bSqrt ) {
         MyPrint( "The bSqrt button was pressed. And pendingOp is " + pendingOp  );

         MySqrt() ;
      }
      else  if ( e.getSource() == bOneDivX ) {
         MyPrint( "The bOneDivX button was pressed. And pendingOp is " + pendingOp  );
         OneDivX() ;
      }
      else  if ( e.getSource() == bCAll ) {
         MyPrint( "The CAll button was pressed." );
         enterBox.setText( "" )  ;
         firstNum = "" ;
         firstNumSet = false ;
         secondNum =  ""   ;
         answer= 0.0 ;
         opCode = -1 ;
      }
      else  if ( e.getSource() == bEquals ) {
         MyPrint( "The Equals button was pressed. And pendingOp is " + pendingOp  +
             " The opCode value is " + opCode );

         if ( pendingOp ) {
            opCodeMethod() ;
            opCode = -1 ;
         }

         secondNum = enterBox.getText()  ;

         opCodeMethod() ;

         firstNum = "" + answer ;
         result = true ;
      }
      else  if ( e.getSource() == bPlusMinus ) {
         MyPrint( "The PlusMinus  button was pressed." );
         opCode = -1 ;

         PlusMinus() ;
      }
      else  if ( e.getSource() == bExp ) {
         MyPrint( "The Power button was pressed." );
         secondNumSet = false ;
         powerOp() ;
      }
      else  if ( e.getSource() == bMPlus ) {
         MyPrint( "1a: The MPlus button was pressed. pendingOp =s " + pendingOp);
         if ( dArrayPtr < 10 ) { /** array hold 10 entries */
            if ( !pendingOp  ) {
               if ( opCode == 99 ) {
                  //enterBox.setText( "" );
                  MyPrint( "1b: The value of opCode is " + opCode +
                    " firstNum =s " + firstNum + " answer =s " + answer );
                  pendingOpCode = -1 ;
                  firstNumSet = true ;
                  firstNum = "" + answer  ;
                  dArray [ dArrayPtr ] =
                      Double.parseDouble( "" + answer ) ;
                  dArrayPtr++ ;
                  enterBox.setText( firstNum ) ;
                  MyPrint( "1c: The value of dArrayPtr is "  + dArrayPtr );
               }
               else {
                  /** This code is entered if the =s button was not just pressed */
                  firstNum = "" + enterBox.getText(  );
                  firstNumSet = true ;
                  dArray [ dArrayPtr ] =
                      Double.parseDouble( "" + firstNum ) ;
                  dArrayPtr++ ;
                  MyPrint( "1d: The value of dArrayPtr is "  + dArrayPtr );
               }
            }
            else if ( !enterBox.getText().equals( "" ) ) {
               dArray [ dArrayPtr ] =
                      Double.parseDouble( enterBox.getText() ) ;
               dArrayPtr++ ;
               firstNumSet = true ;
               firstNum = "" + answer ;
               MyPrint( "1d: The value of dArrayPtr is "  + dArrayPtr );
            }
            else {
               JOptionPane.showMessageDialog( this, "A number must be entered befor clicking the M+ button" ,
                    "Attempted to store a blank number.",
                    JOptionPane.ERROR_MESSAGE );
            }
         }
         result = true ;
      }
      else  if ( e.getSource() == bMMinus ) {
         /** This code retrieves values from the array stack */
         MyPrint( "The MMinus  button was pressed." ) ;
         if ( dArrayPtr >  0 ) {
            enterBox.setText( "" + dArray [ dArrayPtr-1 ] ) ;
            firstNum = "" + answer ;
            answer = dArray [ dArrayPtr-1 ] ;
            dArrayPtr-- ;
            MyPrint( "1a: The retrieved value is " + answer ) ;
         }
         else {
            enterBox.setText( "" ) ;
            MyPrint( "1b: There are no more values to retrieve." ) ;
            JOptionPane.showMessageDialog( this, "There are no more values to retrieve using M-." ,
                    "No More Values.",
                    JOptionPane.ERROR_MESSAGE );
         }

         
         if ( pendingOp ) {
            secondNumSet = true ;
            secondNum = "" + answer ;
         }
         else {
            MyPrint( "1a- MMinus:  The value of answer is " + answer );
            secondNum = ""  ;
            opCode = -1 ;
            dNum2 = 0.0 ;
            dNum1 = answer ;
            secondNumSet = false ;
            firstNum = "" + answer ;
            firstNumSet = true ;
            pendingOp = false ;
            enterBox.setText( "" + answer ) ;
         }
      }
      
      else  if ( e.getSource() == bFact ) {
         MyPrint( "1a- bFact(): opCode is " + opCode + " firstNum is " + firstNum ) ;
         if ( opCode == 99 ) {
            tempInt = ( int ) answer ;
         }
         else  if ( !enterBox.getText().equals( "" ) ) {
            MyPrint( "1b- bFact(): The value of enterBox.getText() is " + enterBox.getText() );
            tempInt = Integer.parseInt( enterBox.getText() )  ;
            MyPrint( "1c- bFact(): The value of tempInt is " + tempInt );
         }
         else if ( firstNumSet ) {
            MyPrint( "1d- bFact() The value of firstNum is " + firstNum ) ;
            tempInt = (int)Double.parseDouble( firstNum )  ;
            MyPrint( "1e- bFact(): The value of tempInt is " + tempInt );
         }

         MyPrint( "1f- bFact(): The value of tempInt is " + tempInt );
         tempInt2 = 1 ;
         for( int ii = 1 ; ii < tempInt + 1; ii++ ) {
            tempInt2 *= ii ;
         }
         enterBox.setText( "" + tempInt2 ) ;
         
         answer = (double)tempInt2 ;
         MyPrint( "1g- factorial:  The value of answer is " + answer );
         secondNum = ""  ;
         opCode = -1 ;
         dNum2 = 0.0 ;
         dNum1 = answer ;
         secondNumSet = false ;
         firstNum = "" + answer ;
         firstNumSet = true ;
         pendingOp = false ;
      }
      

   } 
   private boolean checkFirstNum( ) {
      boolean retCode = false ;

      if (  !firstNumSet  )  {
         if ( !enterBox.getText().equals( "" ) )  {
            firstNum = enterBox.getText()  ;
            firstNumSet = true ;
            result = true ;
            retCode = true ;
            MyPrint( "1a- In checkFirstNum( ) -- firstNum =s " + firstNum);
         }
      }
      else if ( !firstNum.equals( "" ) ) {
         result = true ;
         retCode = true ;
         MyPrint( "1b- In checkFirstNum( ) firstNumSet is " + firstNumSet );
      }

      return retCode ;
   }
   private void setResults() {

      enterBox.setText( "" + answer );
      firstNum = "" + answer ;
      firstNumSet = true ;
      result  = true ;
      pendingOp = false ;
   }
   private void opCodeMethod() {
      int currentOpCode = -1 ;

      MyPrint( "In opCodeMethod() with an opCode of " + opCode );

      
      if ( pendingOp ) {
         currentOpCode = pendingOpCode ;
         pendingOp = false ;
      }
      else {
         currentOpCode = opCode ;
      }

      switch ( currentOpCode ) {
         case 0 :
            multOp() ;
         break ;
         case 1 :
            divOp() ;
         break ;
         case 2 :
            MyPrint( "opCodeMethod(): opCode =s " + currentOpCode ) ;
            subOp() ;
         break ;
         case 3 :
            MyPrint( "opCodeMethod(): opCode =s " + currentOpCode ) ;
            plusOp() ;
         break ;
         case 4 :
            MyPrint( "Calling PowerOp method." );
            MyPrint( "Case 4: the value of firstNum is " +
                  firstNum + " the value of secondNum is " +
                  secondNum ) ;
            secondNumSet = true ;
            powerOp() ;
         break ;
         case 5 :
         break ;
         default :
            setResults() ;
            firstNumSet = true ;
            secondNum = "0.0" ;
            dNum2 = 0.0 ;
            pendingOp = false ;
            opCode = 99 ;
            MyPrint( "Case default: firstNum =s " + firstNum);
         break ;
      }

      if ( !( opCode == 99 ) )
         opCode = -1 ;

   } 
   private void powerOp() {
      MyPrint( "1a- powerOp: The value of firstNum is " + firstNum +
                " The value of secondNum is " + secondNum
                +
                " The value of answer is " + answer );
      if ( opCode == 99 ) {
         setResults() ;
      }

      if ( firstNumSet )  {

         if ( pendingOp ) {
            opCodeMethod() ;
            pendingOp = false ;
            secondNumSet = false ;
         }
         if ( secondNumSet )  {
            MyPrint( "1b- powerOp: The value of firstNum is " + firstNum +
                " The value of secondNum is " + secondNum
                +
                " The value of answer is " + answer );

            secondNum = enterBox.getText()  ;
            convertNumsToDouble() ;
            answer = Math.pow( dNum1 , dNum2 ) ;
            setResults() ;
            MyPrint( "1c- powerOp: The value of answer is " + answer +
                " The value of opCode is " +  opCode );
         }
      }
      else {
         if ( checkFirstNum() ) {
            MyPrint( "1d- powerOp: The value of answer is " + answer +
                " The value of opCode is " +  opCode );
            pendingOpCode = 4 ;
         }
      }
      opCode = 4 ;
   } 
   private void multOp() {

      if ( pendingOp ) {
            MyPrint( "1a- multOp(): opCode is " + opCode + " The value of firstNum is " + firstNum );
            opCodeMethod() ;
            opCode = -1 ;
      }

      if ( !firstNumSet )  {
         pendingOp = true ;

         if ( opCode == 99 ) {
            firstNum = "" + answer ;
            firstNumSet = true ;
            MyPrint( "1b- multOp(): opCode is " + opCode + " The value of firstNum is " + firstNum );
         }

         if ( checkFirstNum() ) {
            opCode = 0 ;
            MyPrint( "1c- multOp(): opCode is " + opCode + " The value of firstNum is " + firstNum );
            pendingOpCode = 0 ;
         }
      }
      else if ( opCode == 0 ) {
         pendingOp = false ;
         secondNum = enterBox.getText()  ;
         pendingOp = true ;
         MyPrint( "2a- multOp(): opCode is " + opCode + " The value of secondNum is " + secondNum );
         if ( secondNum.equals( "" ) ) {
            secondNum = "1.0" ;
            MyPrint( "2b- multOp(): opCode is " + opCode + " The value of secondNum is " + secondNum );
         }
         MyPrint( "3a- multOp(): opCode is " + opCode + " secondNum is " + secondNum ) ;
         convertNumsToDouble() ;
         answer = dNum1 * dNum2 ;
         setResults() ;
         MyPrint( "3b- multOp(): opCode is " + opCode + " The value of answer is " + answer );
         dNum1 = answer ;
         secondNum = "0.0"  ;
         secondNumSet = true ;
      }
      else {
         pendingOp = true ;
         pendingOpCode = 0 ;
         opCode = 0 ;
         result = true ;
         MyPrint( "3a- multOp(): opCode is " + opCode + " The value of answer is " + answer );
      }
   } 
   private void divOp() {
      MyPrint( "1a- divOp(): opCode is " + opCode + " firstNumSet =s " + firstNumSet  );
      if ( pendingOp ) {
         MyPrint( "1aa- divOp(): opCode is " + opCode  );
         opCodeMethod() ;
         opCode = -1 ;
      }

      if ( !firstNumSet )  {
         pendingOp = true ;

         if ( opCode == 99 ) {
            firstNum = "" + answer ;
            firstNumSet = true ;
            MyPrint( "1b- divOp(): opCode is " + opCode + " The value of firstNum is " + firstNum );
         }

         if ( checkFirstNum() ) {
            MyPrint( "1c- divOp(): opCode is " + opCode + " The value of firstNum is " + firstNum );
            pendingOpCode = 1 ;
            opCode = 1 ;
         }
      }
      else if ( firstNumSet ) {
         if ( pendingOp ) {
            opCodeMethod() ;
            opCode = 1 ;
         }

         secondNum = enterBox.getText() ;
         MyPrint( "2a- divOp(): opCode is " + opCode + " firstNum is " +
               firstNum + " secondNum is " + secondNum ) ;
         if ( secondNum.equals( "" ) || opCode < 0 ) {
            MyPrint( "2b- divOp(): opCode is " + opCode + " firstNum is " +
               firstNum + " secondNum is " + secondNum ) ;
         }
         else {
            convertNumsToDouble() ;
            if (dNum2 != 0.0 ) {
               answer = dNum1 / dNum2 ;
               setResults() ;
               MyPrint( "2- divOp(): opCode is " + opCode + " firstNum is " +
                  firstNum + " secondNum is " + secondNum ) ;
            }
            else {
               JOptionPane.showMessageDialog( this, "The number " +
                    dNum2 + " cannot be used as a denometer" ,
                    "Attempted to Divide by Zero",
                    JOptionPane.ERROR_MESSAGE );
               firstNumSet = false ;
               firstNum = ""  ;
              opCode = -1 ;
            }
         }
         opCode = 1 ;

      }
      
      result = true ;
   }
   private void subOp() {

      if ( pendingOp ) {
         MyPrint( "subOp() 1a: In pendingOp code. The opcode value is " + opCode ) ;
         opCodeMethod() ;
         opCode = 2 ;
         pendingOp = true ;
         pendingOpCode = 2 ;
      }
      else if( !firstNumSet ) {
         firstNum = enterBox.getText()  ;
         pendingOp = true ;
         pendingOpCode = 2 ;
         opCode = 2 ;
         firstNumSet = true ;
         result = true ;
         MyPrint( "1b- subOp()-checkFirstNum(): opCode is " + opCode + " The value of answer is " + answer );
      }
      else if ( opCode == 2 ) {
         pendingOp = false ;

         if ( secondNum.equals( "0.0" ) ) {
            firstNum = "" + answer ;
         }

         secondNum = enterBox.getText()  ;
         if ( secondNum.equals( "" ) ) {
            secondNum = "0.0" ;
         }
         MyPrint( "2a- subOp(): opCode is " + opCode + " secondNum is " + secondNum ) ;
         convertNumsToDouble() ;
         answer = dNum1 - dNum2 ;
         setResults() ;
         MyPrint( "2b- subOp(): opCode is " + opCode + " The value of answer is " + answer );
         secondNum = "0.0"  ;
         opCode = -1 ;
         secondNumSet = true ;
      }
      else {
         pendingOp = true ;
         pendingOpCode = 2 ;
         opCode = 2 ;
         result = true ;
         MyPrint( "3a- subOp(): opCode is " + opCode + " The value of answer is " + answer );
      }
   }
   private void plusOp() {

      if ( pendingOp ) {
         MyPrint( "subOp() 1a: In pendingOp code. The opcode value is " + opCode ) ;
         opCodeMethod() ;
         opCode = 3 ;
         pendingOp = true ;
         pendingOpCode = 3 ;
      }
      else if( !firstNumSet ) {
         firstNum = enterBox.getText()  ;
         pendingOp = true ;
         pendingOpCode = 3 ;
         opCode = 3 ;
         firstNumSet = true ;
         result = true ;
         MyPrint( "1b- plusOp()-checkFirstNum(): opCode is " + opCode + " The value of answer is " + answer );
      }
      else if ( opCode == 3 ) {
         pendingOp = false ;
         MyPrint( "2aa- plusOp(): opCode is " + opCode + " secondNum is " + secondNum ) ;
         if ( secondNum.equals( "0.0" ) ) {
            firstNum = "" + answer ;
         }

         secondNum = enterBox.getText()  ;
         if ( secondNum.equals( "" ) ) {
            secondNum = "0.0" ;
         }
         MyPrint( "2a- plusOp(): opCode is " + opCode + " secondNum is " + secondNum ) ;
         convertNumsToDouble() ;
         answer = dNum1 + dNum2 ;
         setResults() ;
         MyPrint( "2b- plusOp(): opCode is " + opCode + " The value of answer is " + answer );
         secondNum = "0.0"  ;
         opCode = -1 ;
         secondNumSet = true ;
      }
      else {
         pendingOp = true ;
         pendingOpCode = 3 ;
         opCode = 3 ;
         result = true ;
         MyPrint( "3a- plusOp(): opCode is " + opCode + " The value of answer is " + answer );
      }
   }
   private void convertNumsToDouble() {
      if ( !firstNum.equals( "" ) )
         dNum1 = Double.parseDouble( firstNum ) ;
      if ( !secondNum.equals( "" ) )
         dNum2 = Double.parseDouble( secondNum ) ;
      MyPrint( "convertNumsToDouble(): The value of dNum1 is " + dNum1 );
      MyPrint( "convertNumsToDouble(): The value of dNum2 is " + dNum2 );

   }
   private void PlusMinus() {
      MyPrint( "PlusMinus: The value of enterBox is " + enterBox.getText() );

      if ( !enterBox.getText().equals( "" ) ) {
         answer = Double.parseDouble( enterBox.getText() ) * minusOne ;
         MyPrint( "1a- PlusMinus: The value of answer is " + answer );
         setResults() ;
      }
      else if ( !firstNum.equals( "" ) ) {
         answer = Double.parseDouble( firstNum ) * minusOne ;
         setResults() ;
         MyPrint( "1b- PlusMinus: The value of firstNum is " + firstNum );
      }
      result = true ;

   } 
   private void MySqrt() {
      MyPrint( "1a- MySqrt(): opCode is " + opCode + " firstNum is " + firstNum ) ;
      if ( opCode == 99 ) {
         answer = Math.sqrt( answer ) ;
         setResults() ;
      }
      else if ( !enterBox.getText().equals( "" ) ) {
         MyPrint( "1b- MySqrt(): opCode is " + opCode + " firstNum is " + firstNum ) ;
         if ( pendingOp )
            opCodeMethod() ;
         answer = Double.parseDouble( enterBox.getText() ) ;
         answer = Math.sqrt( answer ) ;
         setResults() ;
      }
      else if ( !firstNum.equals( "" ) ) {
         MyPrint( "1c- MySqrt(): opCode is " + opCode + " firstNum is " + firstNum ) ;
         answer = Double.parseDouble( firstNum ) ;
         answer = Math.sqrt( answer ) ;
         setResults() ;
      }
      

   } 
   private void OneDivX() {
      MyPrint( "1a- OneDivX(): opCode is " + opCode + " firstNum is " + firstNum ) ;
      if ( opCode == 99 ) {
         answer = ( 1.0 / answer ) ;
         setResults() ;
      }
      else if ( !enterBox.getText().equals( "" ) ) {
         answer = Double.parseDouble( enterBox.getText() ) ;
         answer = ( 1.0 / answer ) ;
         setResults() ;
      }
      else if ( firstNumSet ) {
         answer = Double.parseDouble( firstNum ) ;
         answer = ( 1.0 / answer ) ;
         setResults() ;
      }
   }
   private void MyPrint( String str )   {
      if ( myDiag )
         System.out.println( str );

   } 


 /*  public static void main( String args[] ) {
   	new LoginWindow().setVisible(true);

   }*/

}