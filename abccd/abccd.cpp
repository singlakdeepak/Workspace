//============================================================================
// Name        : abccd.cpp
// Author      : deepak
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C, Ansi-style
//============================================================================

#include <stdio.h>
#include <stdlib.h>
#include "opencv2/highgui/highgui.hpp"
#include <iostream>
using namespace cv;
using namespace std;
int main(int argc, char** argv)
{
  Mat im = imread(argc == 2 ? argv[1] : "lenna.png", 1);
  if (im.empty())
  {
    cout << "Cannot open image!" << endl;
    return -1;
  }
  imshow("image", im);
  waitKey(0);
  return 0;
}
