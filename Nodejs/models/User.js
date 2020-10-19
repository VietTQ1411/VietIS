const {
  DataTypes
} = require('sequelize');

module.exports = sequelize => {
  const attributes = {
    id: {
      type: DataTypes.INTEGER,
      allowNull: false,
      defaultValue: null,
      primaryKey: true,
      autoIncrement: true,
      comment: null,
      field: "id"
    },
    email: {
      type: DataTypes.STRING(50),
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "email"
    },
    name: {
      type: nvarchar(250),
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "name"
    },
    hashPassword: {
      type: nvarchar(250),
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "hashPassword"
    },
    tokenKey: {
      type: nvarchar(250),
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "tokenKey"
    },
    phoneNumber: {
      type: DataTypes.STRING(50),
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "phoneNumber"
    },
    expiredDate: {
      type: DataTypes.DATEONLY,
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "expiredDate"
    },
    userType: {
      type: DataTypes.STRING(50),
      allowNull: true,
      defaultValue: null,
      primaryKey: false,
      autoIncrement: false,
      comment: null,
      field: "userType"
    }
  };
  const options = {
    tableName: "User",
    comment: "",
    indexes: []
  };
  const UserModel = sequelize.define("User_model", attributes, options);
  return UserModel;
};