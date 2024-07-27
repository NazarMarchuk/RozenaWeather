QT       += core gui
QT       += sql
QT       += network

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

CONFIG += c++17

INCLUDEPATH += headers

# You can make your code fail to compile if it uses deprecated APIs.
# In order to do so, uncomment the following line.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

SOURCES += \
    sources/dbmanager.cpp \
    sources/form.cpp \
    sources/hash.cpp \
    sources/login.cpp \
    sources/main.cpp \
    sources/popupdialog.cpp \
    sources/register.cpp \
    sources/web.cpp

HEADERS += \
    headers/dbmanager.h \
    headers/form.h \
    headers/hash.h \
    headers/login.h \
    headers/popupdialog.h \
    headers/register.h \
    headers/web.h

FORMS +=

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target

RESOURCES += \
    resources.qrc
