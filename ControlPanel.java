import java.applet.*;
import java.awt.*;

public class ControlPanel extends Frame 
{
  Kernel kernel ;
  Button runButton = new Button("run");
  Button stepButton = new Button("step");
  Button resetButton = new Button("reset");
  Button exitButton = new Button("exit");
  int pages = 64;
  Button[] pageButtons = new Button[pages];
  Label[] pageLabels = new Label[pages];
  Label statusValueLabel = new Label("STOP" , Label.LEFT) ;
  Label timeValueLabel = new Label("0" , Label.LEFT) ;
  Label instructionValueLabel = new Label("NONE" , Label.LEFT) ;
  Label addressValueLabel = new Label("NULL" , Label.LEFT) ;
  Label pageFaultValueLabel = new Label("NO" , Label.LEFT) ;
  Label virtualPageValueLabel = new Label("x" , Label.LEFT) ;
  Label physicalPageValueLabel = new Label("0" , Label.LEFT) ;
  Label RValueLabel = new Label("0" , Label.LEFT) ;
  Label MValueLabel = new Label("0" , Label.LEFT) ;
  Label inMemTimeValueLabel = new Label("0" , Label.LEFT) ;
  Label lastTouchTimeValueLabel = new Label("0" , Label.LEFT) ;
  Label lowValueLabel = new Label("0" , Label.LEFT) ;
  Label highValueLabel = new Label("0" , Label.LEFT) ;

  Choice pageReplacingAlgChoice = new Choice() ;

  public ControlPanel() 
  {
    super();
  }

  public ControlPanel( String title ) 
  {
    super(title);
  }

  public void init( Kernel useKernel , String commands , String config ) 
  {
    kernel = useKernel ;
    kernel.setControlPanel( this );
    setLayout( null );
    setBackground( Color.white );
    setForeground( Color.black );
    setSize( 635 , 545 );
    setFont( new Font( "Courier", 0, 12 ) );

    int buttonWidth = 70;
    int height = 15;

    int controlBtnLineOffsetY = 0;
    runButton.setForeground( Color.blue );
    runButton.setBackground( Color.lightGray );
    runButton.reshape( controlBtnLineOffsetY,25,buttonWidth,height );
    add( runButton );    

    stepButton.setForeground( Color.blue );
    stepButton.setBackground( Color.lightGray );
    stepButton.reshape( controlBtnLineOffsetY+=buttonWidth,25,buttonWidth,height );
    add( stepButton );

    resetButton.setForeground( Color.blue );
    resetButton.setBackground( Color.lightGray );
    resetButton.reshape( controlBtnLineOffsetY+=buttonWidth,25,buttonWidth,height );
    add( resetButton );

    exitButton.setForeground( Color.blue );
    exitButton.setBackground( Color.lightGray );
    exitButton.reshape( controlBtnLineOffsetY+=buttonWidth,25,buttonWidth,height );
    add( exitButton );

    pageReplacingAlgChoice.reshape(controlBtnLineOffsetY+=buttonWidth,25,buttonWidth + 25,height);
    pageReplacingAlgChoice.add("FIFO");
    pageReplacingAlgChoice.add("LRU (matrix)");
    add(pageReplacingAlgChoice);

    for (int i = 0; i < pages; i++)
    {
      pageButtons[i] = new Button("page " + i);
      pageButtons[i].reshape(
              i < pages / 2 ? 0 : 140,
              ((i % (pages / 2)) + 2)*15+25,
              buttonWidth,
              height
      );
      pageButtons[i].setForeground( Color.magenta );
      pageButtons[i].setBackground( Color.lightGray );
      add(pageButtons[i]);
    }

    // place labels lower than control buttons
    int labelsLineBaseOffsetY = height + 20;
    statusValueLabel.reshape( 345,labelsLineBaseOffsetY+0+25,100,height );
    add( statusValueLabel );

    timeValueLabel.reshape( 345,labelsLineBaseOffsetY+15+25,100,height );
    add( timeValueLabel );

    instructionValueLabel.reshape( 385,labelsLineBaseOffsetY+45+25,100,height );
    add( instructionValueLabel );

    addressValueLabel.reshape(385,labelsLineBaseOffsetY+60+25,230,height);
    add( addressValueLabel );

    pageFaultValueLabel.reshape( 385,labelsLineBaseOffsetY+90+25,100,height );
    add( pageFaultValueLabel );

    virtualPageValueLabel.reshape( 395,labelsLineBaseOffsetY+120+25,200,height );
    add( virtualPageValueLabel );

    physicalPageValueLabel.reshape( 395,labelsLineBaseOffsetY+135+25,200,height );
    add( physicalPageValueLabel );

    RValueLabel.reshape( 395,labelsLineBaseOffsetY+150+25,200,height );
    add( RValueLabel );

    MValueLabel.reshape( 395,labelsLineBaseOffsetY+165+25,200,height );
    add( MValueLabel );

    inMemTimeValueLabel.reshape(395,labelsLineBaseOffsetY+180+25,200,height );
    add( inMemTimeValueLabel );

    lastTouchTimeValueLabel.reshape( 395,labelsLineBaseOffsetY+195+25,200,height );
    add( lastTouchTimeValueLabel );

    lowValueLabel.reshape( 395,labelsLineBaseOffsetY+210+25,230,height );
    add( lowValueLabel );

    highValueLabel.reshape( 395,labelsLineBaseOffsetY+225+25,230,height );
    add( highValueLabel );

    Label virtualOneLabel = new Label( "virtual" , Label.CENTER) ;
    virtualOneLabel.reshape(0,15+25,70,height);
    add(virtualOneLabel);

    Label virtualTwoLabel = new Label( "virtual" , Label.CENTER) ;
    virtualTwoLabel.reshape(140,15+25,70,height);
    add(virtualTwoLabel);

    Label physicalOneLabel = new Label( "physical" , Label.CENTER) ;
    physicalOneLabel.reshape(70,15+25,70,height);
    add(physicalOneLabel);

    Label physicalTwoLabel = new Label( "physical" , Label.CENTER) ;
    physicalTwoLabel.reshape(210,15+25,70,height);
    add(physicalTwoLabel);

    Label statusLabel = new Label("status: " , Label.LEFT) ;
    statusLabel.reshape(285,labelsLineBaseOffsetY+0+25,65,height);
    add(statusLabel);

    Label timeLabel = new Label("time: " , Label.LEFT) ;
    timeLabel.reshape(285,labelsLineBaseOffsetY+15+25,50,height);
    add(timeLabel);

    Label instructionLabel = new Label("instruction: " , Label.LEFT) ;
    instructionLabel.reshape(285,labelsLineBaseOffsetY+45+25,100,height);
    add(instructionLabel);

    Label addressLabel = new Label("address: " , Label.LEFT) ;
    addressLabel.reshape(285,labelsLineBaseOffsetY+60+25,85,height);
    add(addressLabel);

    Label pageFaultLabel = new Label("page fault: " , Label.LEFT) ;
    pageFaultLabel.reshape(285,labelsLineBaseOffsetY+90+25,100,height);
    add(pageFaultLabel);

    Label virtualPageLabel = new Label("virtual page: " , Label.LEFT) ;
    virtualPageLabel.reshape(285,labelsLineBaseOffsetY+120+25,110,height);
    add(virtualPageLabel);

    Label physicalPageLabel = new Label("physical page: " , Label.LEFT) ;
    physicalPageLabel.reshape(285,labelsLineBaseOffsetY+135+25,110,height);
    add(physicalPageLabel);

    Label RLabel = new Label("R: ", Label.LEFT) ;
    RLabel.reshape(285,labelsLineBaseOffsetY+150+25,110,height);
    add(RLabel);

    Label MLabel = new Label("M: " , Label.LEFT) ;
    MLabel.reshape(285,labelsLineBaseOffsetY+165+25,110,height);
    add(MLabel);

    Label inMemTimeLabel = new Label("inMemTime: " , Label.LEFT) ;
    inMemTimeLabel.reshape(285,labelsLineBaseOffsetY+180+25,110,height);
    add(inMemTimeLabel);

    Label lastTouchTimeLabel = new Label("lastTouchTime: " , Label.LEFT) ;
    lastTouchTimeLabel.reshape(285,labelsLineBaseOffsetY+195+25,110,height);
    add(lastTouchTimeLabel);

    Label lowLabel = new Label("low: " , Label.LEFT) ;
    lowLabel.reshape(285,labelsLineBaseOffsetY+210+25,110,height);
    add(lowLabel);

    Label highLabel = new Label("high: " , Label.LEFT) ;
    highLabel.reshape(285,labelsLineBaseOffsetY+225+25,110,height);
    add(highLabel);

    for (int i = 0; i < pages; i++)
    {
      pageLabels[i] = new Label("page " + i);

      int y = (i % (pages / 2) + 2) * 15 +25;

      pageLabels[i].reshape(
              i < pages / 2 ? 70 : 210,
              y,
              70,
              height
      );
      pageLabels[i].setForeground( Color.red );
      pageLabels[i].setFont( new Font( "Courier", 0, 10 ) );
      add(pageLabels[i]);
    }

    kernel.init( commands , config );

    show();
  }

  public void paintPage( Page page ) 
  {
    virtualPageValueLabel.setText( Integer.toString( page.id ) );
    physicalPageValueLabel.setText( Integer.toString( page.physical ) );
    RValueLabel.setText( Integer.toString( page.R ) );
    MValueLabel.setText( Integer.toString( page.M ) );
    inMemTimeValueLabel.setText( Integer.toString( page.inMemTime ) );
    lastTouchTimeValueLabel.setText( Integer.toString( page.lastTouchTime ) );
    lowValueLabel.setText(Long.toString( page.low , Kernel.addressradix ) );
    highValueLabel.setText(Long.toString( page.high , Kernel.addressradix ) );
  }

  public void setStatus(String status) {
    statusValueLabel.setText(status);
  }

  public void addPhysicalPage( int pageNum , int physicalPage ) 
  {
    if (physicalPage >= 0 && physicalPage < pages)
      pageLabels[physicalPage].setText("page " + pageNum);
    // FIXME: do something on else?
  }

  public void removePhysicalPage( int physicalPage ) 
  {
    if (physicalPage >= 0 && physicalPage < pages)
      pageLabels[physicalPage].setText(null);
  }


  public boolean action( Event e, Object arg ) {
    if (e.target == runButton) {
      setStatus("RUN");
      runButton.disable();
      stepButton.disable();
      resetButton.disable();
      kernel.setPageReplacementAlgorithm(pageReplacingAlgChoice.getSelectedItem());
      kernel.run();
      setStatus("STOP");
      resetButton.enable();
      return true;
    } else if (e.target == stepButton) {
      setStatus("STEP");
      kernel.step();
      if (kernel.runcycles == kernel.runs) {
        stepButton.disable();
        runButton.disable();
      }
      setStatus("STOP");
      return true;
    } else if (e.target == resetButton) {
      kernel.reset();
      runButton.enable();
      stepButton.enable();
      return true;
    } else if (e.target == exitButton) {
      System.exit(0);
      return true;
    } else {
      for (int i = 0; i < pages; i++)
        if (e.target.equals(pageButtons[i])) {
          kernel.getPage(i);
          return true;
        }
    }
    return false;
  }
}
