#ifndef DBMANAGER_H
#define DBMANAGER_H
#include <QString>
#include <QSqlDatabase>
#include <QtSql>

class DbManager
{
public:
    DbManager(const QString& path);
    bool insertUserLoginData(const QString& login, const QString&  password);
    bool checkIfUserExist(const QString& login);
    bool checkPassword(const QString& login, const QString&  password);
private:
    QSqlDatabase m_db;
};
#endif // DBMANAGER_H
