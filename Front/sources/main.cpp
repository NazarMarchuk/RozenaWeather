#include "mainLayout.h"
#include "dbmanager.h"
#include "web.h"
#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    a.setWindowIcon(QIcon(":/new/prefix1/Cloudy_icon.png"));
    Form form;
    //Web obj1;
    return a.exec();
}
