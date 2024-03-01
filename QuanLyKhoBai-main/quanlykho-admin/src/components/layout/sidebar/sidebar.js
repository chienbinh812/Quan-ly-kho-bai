import React, { useEffect, useState } from 'react';
import "./sidebar.css";
import { Layout, Menu } from 'antd';
import { useHistory, useLocation } from "react-router-dom";
import { UserOutlined, ContainerOutlined, DashboardOutlined, BarsOutlined, BgColorsOutlined, ShoppingOutlined, AuditOutlined, ShoppingCartOutlined, FormOutlined, NotificationOutlined } from '@ant-design/icons';

const { SubMenu } = Menu;
const { Sider } = Layout;

function Sidebar() {

  const history = useHistory();
  const location = useLocation();

  const menuSidebarAdmin = [
    {
      key: "account-management",
      title: "Quản Lý tài khoản",
      link: "/account-management",
      icon: <UserOutlined />
    },
    {
      key: "category-list",
      title: "Quản lý sản phẩm",
      link: "/category-list",
      icon: <ShoppingOutlined />
    },
    {
      key: "subcategory-list",
      title: "Quản lý kho hàng",
      link: "/subcategory-list",
      icon: <FormOutlined />
    },
  ];

  const navigate = (link, key) => {
    history.push(link);
  }

  useEffect(() => {
  })

  return (
    <Sider
      className={'ant-layout-sider-trigger'}
      width={215}
      style={{
        position: "fixed",
        top: 60,
        height: '100%',
        left: 0,
        padding: 0,
        zIndex: 1,
        marginTop: 0,
        boxShadow: " 0 1px 4px -1px rgb(0 0 0 / 15%)"
      }}
    >
     <Menu
          mode="inline"
          selectedKeys={location.pathname.split("/")}
          defaultOpenKeys={['account']}
          style={{ height: '100%', borderRight: 0, backgroundColor: "#FFFFFF" }}
          theme='light'
        >

          {
            menuSidebarAdmin.map((map) => (
              <Menu.Item
                onClick={() => navigate(map.link, map.key)}
                key={map.key}
                icon={map.icon}
                className="customeClass"
              >
                {map.title}
              </Menu.Item>
            ))
          }
        </Menu>

    </Sider >
  );
}

export default Sidebar;