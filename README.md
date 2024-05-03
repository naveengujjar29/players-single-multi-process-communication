# Player Communication Support - Single Process vs Multi-Process using Socket

## Prerequisites

### Maven Installation

If Maven is not installed on your system, follow these steps to install it:

1. Download Maven:
   ```bash
    wget https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz
   ```

2. Extract Maven:
   ```bash
   tar xvf apache-maven-3.9.6-bin.tar.gz
   ```

3. Set up environment variables:
   ```bash
   export M2_HOME=/usr/local/apache-maven/apache-maven-3.9.6
   export M2=$M2_HOME/bin
   export PATH=$M2:$PATH
   ```

4. Verify Maven installation:
   ```bash
   mvn -version
   ```

### JDK 17 Installation

To install JDK 17, you can use Amazon Corretto JDK 17. Follow these steps:

1. Import Corretto public key:
   ```bash
   sudo rpm --import https://yum.corretto.aws/corretto.key
   ```

2. Add Corretto repository:
   ```bash
   sudo curl -L -o /etc/yum.repos.d/corretto.repo https://yum.corretto.aws/corretto.repo
   ```

3. Install JDK 17:
   ```bash
   sudo yum install -y java-17-amazon-corretto-devel
   ```

4. Set up JAVA_HOME environment variable:
   ```bash
   export JAVA_HOME=/usr/lib/jvm/java-17-amazon-corretto/
   ```

5. Verify JDK installation:
   ```bash
   java --version
   ```

## Running the Scripts

Once Maven and JDK 17 are installed and configured, you can run the provided scripts.

1. Run the scripts for player communication:
   - **Single Process**: Execute the script `startup-single-process-players.sh` for single-process communication.
   - **Multi-Process**: Execute the script `startup-multi-process-players.sh` for multi-process communication.
