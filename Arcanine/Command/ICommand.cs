using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Meebey.SmartIrc4net;

namespace Arcanine.Command
{
    interface ICommand
    {
        void execute(IrcEventArgs e);
    }
}
