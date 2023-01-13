import './Layout.css';
function Layout(props){
    return(
        <div className="container flex flex_ver flex_center_all">
            <header></header>
            <section className="flex_1">{props.children}</section>
            <footer></footer>
        </div>
    );
}
export default Layout;