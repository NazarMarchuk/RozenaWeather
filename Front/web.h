#ifndef WEB_H
#define WEB_H

#include <QApplication>
#include <QNetworkAccessManager>
#include <QNetworkReply>
#include <QWidget>
#include <QLabel>
#include <QJsonDocument>
#include <QJsonObject>
#include <QJsonValue>
#include <QString>

class Web : public QWidget
{
    Q_OBJECT
private:
    QWidget* window;
    QLabel* label;
    QNetworkAccessManager* manager;
    QNetworkReply* reply;
public:
    explicit Web(QWidget *parent = nullptr);
private slots:
    void processResponse();
signals:
};

#endif // WEB_H
