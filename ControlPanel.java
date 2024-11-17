import java.applet.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ControlPanel extends Frame 
{
  private static final int INSET = 8;
  private int pages;
  Panel pagePanel;
  Kernel kernel ;
  ScrollPane scrollPagePane = new ScrollPane();
  Button runButton = new Button("run");
  Button stepButton = new Button("step");
  Button resetButton = new Button("reset");
  Button exitButton = new Button("exit");
  Button[] pageButtons;
  Label[] pageLabels;
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

  Choice pageReplacingAlgChoice = new Choice();
  TextField cycleStepSleepTime = new TextField("1000");

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
    setLayout( new CardLayout(10, 20) );
    setBackground( Color.white );
    setForeground( Color.black );
    setSize( 635 + INSET, 545 + INSET );
    setFont( new Font( "Courier", 0, 12 ) );

    // dispose frame on titlebar close button pressed
    addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent we) {
                          dispose();
                        }
                      }
    );

    Panel panel = new Panel(new BorderLayout());
    Panel controlBtnPanel = new Panel(new FlowLayout(FlowLayout.LEFT));

    runButton.setForeground(Color.blue);
    runButton.setBackground(Color.lightGray);
    controlBtnPanel.add(runButton);

    stepButton.setForeground(Color.blue);
    stepButton.setBackground(Color.lightGray);
    controlBtnPanel.add(stepButton);

    resetButton.setForeground(Color.blue);
    resetButton.setBackground(Color.lightGray);
    controlBtnPanel.add(resetButton);

    exitButton.setForeground(Color.blue);
    exitButton.setBackground(Color.lightGray);
    controlBtnPanel.add(exitButton);

    panel.add(controlBtnPanel, BorderLayout.NORTH);
    add(panel);

    panel.add(scrollPagePane, BorderLayout.CENTER);

    // set up status labels
    Panel rightPanel = new Panel(new GridLayout(16, 2));
    Label cycleStepSleepTimeLabel = new Label("Cycle step sleep time:" , Label.LEFT);
    rightPanel.add(cycleStepSleepTimeLabel);
    rightPanel.add(cycleStepSleepTime);

    Label pageReplacingAlgLabel = new Label("Page replacing algorithm:" , Label.LEFT);
    rightPanel.add(pageReplacingAlgLabel);
    pageReplacingAlgChoice.add("FIFO");
    pageReplacingAlgChoice.add("LRU (matrix)");
    rightPanel.add(pageReplacingAlgChoice);

    Label statusLabel = new Label("status: ", Label.LEFT);
    rightPanel.add(statusLabel);
    rightPanel.add(statusValueLabel);

    Label timeLabel = new Label("time: ", Label.LEFT);
    rightPanel.add(timeLabel);
    rightPanel.add(timeValueLabel);

    Label instructionLabel = new Label("instruction: ", Label.LEFT);
    rightPanel.add(instructionLabel);
    rightPanel.add(instructionValueLabel);

    Label addressLabel = new Label("address: ", Label.LEFT);
    rightPanel.add(addressLabel);
    rightPanel.add(addressValueLabel);

    Label pageFaultLabel = new Label("page fault: ", Label.LEFT);
    rightPanel.add(pageFaultLabel);
    rightPanel.add(pageFaultValueLabel);

    Label virtualPageLabel = new Label("virtual page: ", Label.LEFT);
    rightPanel.add(virtualPageLabel);
    rightPanel.add(virtualPageValueLabel);

    Label physicalPageLabel = new Label("physical page: ", Label.LEFT);
    rightPanel.add(physicalPageLabel);
    rightPanel.add(physicalPageValueLabel);

    Label RLabel = new Label("R: ", Label.LEFT);
    rightPanel.add(RLabel);
    rightPanel.add(RValueLabel);

    Label MLabel = new Label("M: ", Label.LEFT);
    rightPanel.add(MLabel);
    rightPanel.add(MValueLabel);

    Label inMemTimeLabel = new Label("inMemTime: ", Label.LEFT);
    rightPanel.add(inMemTimeLabel);
    rightPanel.add(inMemTimeValueLabel);

    Label lastTouchTimeLabel = new Label("lastTouchTime: ", Label.LEFT);
    rightPanel.add(lastTouchTimeLabel);
    rightPanel.add(lastTouchTimeValueLabel);

    Label lowLabel = new Label("low: ", Label.LEFT);
    rightPanel.add(lowLabel);
    rightPanel.add(lowValueLabel);

    Label highLabel = new Label("high: ", Label.LEFT);
    rightPanel.add(highLabel);
    rightPanel.add(highValueLabel);

    panel.add(rightPanel, BorderLayout.EAST);

    kernel.init( commands , config );
    show();
  }

  public void initPageButtons(int virtPageNum) {
    pages = virtPageNum + 1;

    if (pagePanel != null) {
      pagePanel.removeAll();
    } else {
      pagePanel = new Panel(new GridLayout(pages + 1, 2, 5, 5));
      scrollPagePane.add(pagePanel);
    }

    // set up page labels and buttons
    Label virtualTwoLabel = new Label("virtual", Label.CENTER);
    pagePanel.add(virtualTwoLabel);

    Label physicalOneLabel = new Label("physical", Label.CENTER);
    pagePanel.add(physicalOneLabel);

    // page btns and labels
    pageButtons = new Button[pages];
    pageLabels = new Label[pages];

    for (int i = 0; i < pages; i++) {
      pageButtons[i] = new Button("page " + i);
      pageButtons[i].setForeground(Color.magenta);
      pageButtons[i].setBackground(Color.lightGray);
      pagePanel.add(pageButtons[i]);

      pageLabels[i] = new Label("page " + i);
      pageLabels[i].setForeground(Color.red);
      pageLabels[i].setFont(new Font("Courier", 0, 10));
      pagePanel.add(pageLabels[i]);
    }

    revalidate();
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
      kernel.cycleStepSleepTime = Integer.parseInt(cycleStepSleepTime.getText());
      kernel.run();
      setStatus("STOP");
      resetButton.enable();
      return true;
    } else if (e.target == stepButton) {
      setStatus("STEP");

      if (kernel.runs == 0) {
        kernel.setPageReplacementAlgorithm(pageReplacingAlgChoice.getSelectedItem());
      }

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

  @Override
  public Insets getInsets() {
    return new Insets(INSET, INSET, INSET, INSET);
  }
}
