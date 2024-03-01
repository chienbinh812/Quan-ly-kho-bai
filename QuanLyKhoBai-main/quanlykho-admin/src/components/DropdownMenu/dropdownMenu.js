import React, { useEffect, useState } from 'react';
import "./dropdownMenu.css";
import { useHistory } from "react-router-dom";
import { Avatar, Dropdown, Row, Menu } from 'antd';
import { UserOutlined, SettingOutlined, LogoutOutlined } from '@ant-design/icons';
import userApi from "../../apis/userApi";

function DropdownAvatar() {

  const [userData, setUserData] = useState([]);
  let history = useHistory();

  const Logout = async () => {
    localStorage.clear();
    history.push("/");
  }

  useEffect(() => {
    (async () => {
      try {
        const data = JSON.parse(localStorage.getItem("user"));
        setUserData(data);
      } catch (error) {
        console.log('Failed to fetch profile user:' + error);
      }
    })();
  }, [])

  const handleRouter = (link) => {
    history.push(link);
  }

  const avatar = (
    <Menu>
      <Menu.Item key="3" icon={<LogoutOutlined />} onClick={Logout}  >
        <a target="_blank" rel="noopener noreferrer" >
          Thoát
        </a>
      </Menu.Item>
    </Menu>
  );


  return (
    <Dropdown key="avatar" placement="bottomCenter" overlay={avatar} arrow>
      <Row
        style={{
          paddingLeft: 5, paddingRight: 5, cursor: 'pointer', padding: 9
        }}
        className="container"
      >
        <div style={{ display: 'flex', alignItems: "center", justifyContent: "center" }}>
          <div style={{ paddingRight: 10 }}>
            <Avatar
              style={{
                outline: 'none',
              }}
              src={userData.image ? userData.image : "https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"}/>
          </div>
          <p style={{ padding: 0, margin: 0, textTransform: 'capitalize', color: "#000000" }} >
            {userData?.username}
          </p>
        </div>
      </Row>
    </Dropdown>
  );
};

export default DropdownAvatar;